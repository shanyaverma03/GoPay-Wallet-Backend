package com.stackroute.UserService.service;

import com.stackroute.UserService.exceptions.TransactionNotCreatedException;
import com.stackroute.UserService.exceptions.UserAlreadyExistsException;
import com.stackroute.UserService.exceptions.UserNotFoundException;
import com.stackroute.UserService.model.Conversion;
import com.stackroute.UserService.model.Transaction;
import com.stackroute.UserService.model.User;
import com.stackroute.UserService.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /*
     * This method should be used to save a new user.Call the corresponding method
     * of Respository interface.
     */

    public User registerUser(User user) throws UserAlreadyExistsException {

        User userSaved = null;
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser!=null) {
            userSaved = null;
            throw new UserAlreadyExistsException("User Already Exists");
        }


        UUID uuid= UUID.randomUUID();

        user.setUserId(ObjectId.get());

        HashMap<String, Double> map = new HashMap<>();
        map.put("inr" , 0.0);
        map.put("euro" , 0.0);
        map.put("usd" , 0.0);
        map.put("cny" , 0.0);
        user.setCurrencies(map);


        User user1 = userRepository.save(user);
        userSaved = userRepository.insert(user1);
        if (userSaved == null) {
            throw new UserAlreadyExistsException("Null");
        }
        return userSaved;
    }


    public User updateUser(String username, User user) throws UserNotFoundException {
        User updatedUser = userRepository.findByUsername(username);

        if (updatedUser != null) {
            updatedUser.setUserId(user.getUserId());
            updatedUser.setUsername(user.getUsername());
            updatedUser.setUserPassword(user.getUserPassword());
            updatedUser.setBaseCurrency(user.getBaseCurrency());
            updatedUser.setContact(user.getContact());

            userRepository.save(updatedUser);
            return updatedUser;
        } else {
            throw new UserNotFoundException("User does not exist");
        }
    }


    @Override
    public HashMap<String, Double> currenciesById(String username) throws UserNotFoundException {
       User foundUser = userRepository.findByUsername(username);
        if (foundUser==null) {
            throw new UserNotFoundException("user does not exist");
        }
        return  foundUser.getCurrencies();
    }

    @Override
    public List<Conversion> convertCurrency(Conversion conversion) throws UserNotFoundException {

        conversion.setDateOfTransfer(new Date());

       User user = userRepository.findByUsername(conversion.getUsername());
        if (user==null) {
            throw new UserNotFoundException("user does not exist");
        }
        UUID uuid= UUID.randomUUID();
        conversion.setConversionId( uuid.hashCode());
        user.getConversions().add(conversion);
        HashMap<String, Double> initialCurrency= user.getCurrencies();
        Iterator iterator= initialCurrency.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry c = (Map.Entry) iterator.next();
            if(c.getKey().equals(conversion.getBaseCurrency())){
                Double initial= (Double) c.getValue();
                Double newValue= initial-conversion.getAmountConverted();
                c.setValue(newValue);
                user.setCurrencies(initialCurrency);

                userRepository.save(user);

            }
            else if(c.getKey().equals(conversion.getNewCurrency())){
                Double initial= (Double) c.getValue();
                Double newValue= initial+conversion.getNewAmount();
                c.setValue(newValue);
                user.setCurrencies(initialCurrency);
                userRepository.save(user);
            }
        }
        userRepository.save(user);
        return user.getConversions();
    }

    @Override
    public List<Conversion> getConversions(String username) throws UserNotFoundException {
        Optional<User> optional = userRepository.findById(username);
        if (!optional.isPresent()) {
            throw new UserNotFoundException("user does not exist");
        }
        return optional.get().getConversions();
    }


    public List<User> getAllUsers() {
        List<User> list = userRepository.findAll();
        return list;
    }

    @Override
    public HashMap<String, Double> addAmountInwallet(String username, Double amount) {
        User user= userRepository.findByUsername(username);
        HashMap<String, Double> initialCurr= user.getCurrencies();
        for(Map.Entry<String, Double> entry: initialCurr.entrySet()){
            if(entry.getKey().equals("inr")){
                Double initial= entry.getValue();
                entry.setValue(initial+amount);
                break;
            }
        }
     user.setCurrencies(initialCurr);
        userRepository.save(user);

        return user.getCurrencies();
    }

    @Override
    public boolean createTransaction(Transaction transaction) throws TransactionNotCreatedException {

        UUID uuid= UUID.randomUUID();
        String currencyUsed= transaction.getCurrencyUsed();
        Double amount= transaction.getAmountTransferred();
        transaction.setTransactionId(uuid.hashCode());
        transaction.setDateOfTransfer(new Date());
        User newUser= new User();
//        newUser.setUserId(transaction.getUsername());
        newUser.setTransactions(new ArrayList<>());
        newUser.getTransactions().add(transaction);
        User addedUser= null;
       User foundUser= userRepository.findByUsername(transaction.getUsername());
        if(foundUser!=null){
            transaction.setStatus("Sent");
            foundUser.getTransactions().add(transaction);


            HashMap<String, Double> currenciesOfUser= foundUser.getCurrencies();
            Double inrNew =0.0;
            Double inrInitial=0.0;
            Double usdNew=0.0;
            Double usdInitial=0.0;
            Double cnyNew=0.0;
            Double cnyInitial=0.0;
            Double euroNew=0.0;
            Double euroInitial=0.0;
            Iterator iterator= currenciesOfUser.entrySet().iterator();


            while(iterator.hasNext()){
                Map.Entry c= (Map.Entry) iterator.next();
                if(c.getKey().equals("inr")){
                    inrInitial= (Double) c.getValue();
                }
                else if(c.getKey().equals("usd")){
                    usdInitial= (Double) c.getValue();
                }
                else if(c.getKey().equals("cny")){
                    cnyInitial= (Double) c.getValue();
                }
                else{
                    euroInitial= (Double) c.getValue();
                }
            }
            Boolean inrflag=false;
            Boolean usdflag=false;
            Boolean cnyflag=false;
            Boolean euroflag=false;

            if(currencyUsed.equals("inr")) {
                inrNew= inrInitial-amount;
                inrflag= true;
            }
            else if(currencyUsed.equals("usd")) {
                usdNew= usdInitial-amount;
                usdflag= true;
            }
            else if(currencyUsed.equals("cny")) {
                cnyNew= cnyInitial-amount;
                cnyflag= true;
            }
            else{
                euroNew= euroInitial-amount;
                euroflag= true;
            }
            Iterator iterator2= currenciesOfUser.entrySet().iterator();
            while(iterator2.hasNext()) {
                Map.Entry c = (Map.Entry) iterator2.next();
                String curr= (String) c.getKey();
                if(curr.equals("inr") && inrflag==true){
                    c.setValue(inrNew);

                }
                else if(curr.equals("usd") && usdflag==true){
                    c.setValue(usdNew);
                }
                else if(curr.equals("cny") && cnyflag==true){
                    c.setValue(cnyNew);
                }
                else if(curr.equals("euro") && euroflag==true){
                    c.setValue(euroNew);
                }
            }
            userRepository.save(foundUser);

            changeCurrencyOfReceiver(transaction);
        }
        else{
            addedUser= userRepository.insert(newUser);

        }

        if(addedUser==null){
            return  false;
        }
        else {
            return  true;
        }

    }

    private void changeCurrencyOfReceiver(Transaction transaction) {
        String accountOfReceiver= transaction.getAccountTransferredTo();
        User receiver= userRepository.findByAccountNumber(accountOfReceiver);
        transaction.setStatus("Received");
        if(receiver!=null){
            HashMap<String, Double> currenciesOfUser= receiver.getCurrencies();
            Double inrNew =0.0;
            Double inrInitial=0.0;
            Double usdNew=0.0;
            Double usdInitial=0.0;
            Double cnyNew=0.0;
            Double cnyInitial=0.0;
            Double euroNew=0.0;
            Double euroInitial=0.0;
            Iterator iterator= currenciesOfUser.entrySet().iterator();

            Double amount= transaction.getAmountTransferred();

            while(iterator.hasNext()){
                Map.Entry c= (Map.Entry) iterator.next();
                if(c.getKey().equals("inr")){
                    inrInitial= (Double) c.getValue();
                }
                else if(c.getKey().equals("usd")){
                    usdInitial= (Double) c.getValue();
                }
                else if(c.getKey().equals("cny")){
                    cnyInitial= (Double) c.getValue();
                }
                else{
                    euroInitial= (Double) c.getValue();
                }
            }
            Boolean inrflag=false;
            Boolean usdflag=false;
            Boolean cnyflag=false;
            Boolean euroflag=false;

            String currencyUsed= transaction.getCurrencyUsed();
            if(transaction.getCurrencyUsed().equals("inr")) {
                inrNew= inrInitial+amount;
                inrflag= true;
            }
            else if(currencyUsed.equals("usd")) {
                usdNew= usdInitial+amount;
                usdflag= true;
            }
            else if(currencyUsed.equals("cny")) {
                cnyNew= cnyInitial+amount;
                cnyflag= true;
            }
            else{
                euroNew= euroInitial+amount;
                euroflag= true;
            }
            Iterator iterator2= currenciesOfUser.entrySet().iterator();
            while(iterator2.hasNext()) {
                Map.Entry c = (Map.Entry) iterator2.next();
                String curr= (String) c.getKey();
                if(curr.equals("inr") && inrflag==true){
                    c.setValue(inrNew);

                }
                else if(curr.equals("usd") && usdflag==true){
                    c.setValue(usdNew);
                }
                else if(curr.equals("cny") && cnyflag==true){
                    c.setValue(cnyNew);
                }
                else if(curr.equals("euro") && euroflag==true){
                    c.setValue(euroNew);
                }
            }
            userRepository.save(receiver);
//            List<Transaction> transactions= receiver.getTransactions();
            if(receiver.getTransactions()==null){
                List<Transaction> transactions= new ArrayList<>();
                transactions.add(transaction);
                receiver.setTransactions(transactions);
            }
            else {
                List<Transaction> transactions= receiver.getTransactions();
                transactions.add(transaction);
                receiver.setTransactions(transactions);
            }
            userRepository.save(receiver);


        }
    }

    @Override
    public List<Transaction> getAllTransactionsByUserId(String userId) {
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()){
            return  user.get().getTransactions();
        }
        else {
            return null;
        }
    }


}
