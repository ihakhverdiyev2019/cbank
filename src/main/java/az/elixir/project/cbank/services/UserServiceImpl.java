package az.elixir.project.cbank.services;

import az.elixir.project.cbank.dto.UserRegisterRequest;
import az.elixir.project.cbank.models.BudgetModel;
import az.elixir.project.cbank.models.TransactionModel;
import az.elixir.project.cbank.models.UserLoginTokenModel;
import az.elixir.project.cbank.models.UserModel;
import az.elixir.project.cbank.repositories.BudgetRepository;
import az.elixir.project.cbank.repositories.UserLoginTokenRepository;
import az.elixir.project.cbank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserLoginTokenRepository userLoginTokenRepository;


    @Override
    public void registerUser(UserRegisterRequest userRegisterRequest) {
        UserModel userModel = new UserModel();
        BudgetModel budgetModel = new BudgetModel();
        userModel.setBirthDate(userRegisterRequest.getBirthDate());
        userModel.setCustomerCode(userRegisterRequest.getCustomerCode());
        userModel.setFirstName(userRegisterRequest.getFirstName());
        userModel.setLastName(userRegisterRequest.getLastName());
        userModel.setEmail(userRegisterRequest.getEmail());
        userModel.setBudget(budgetModel);
        userRepository.save(userModel);
    }

    @Override
    public UserModel getCustomerDataByCustomerCode(String token) {
            UserLoginTokenModel userLoginTokenModel = userLoginTokenRepository.findByToken(token);

        return userLoginTokenModel.getUser();
    }

    @Override
    public UserLoginTokenModel login(String email, String password) {
        UserModel userModel = userRepository.findByEmailAndPassword(email,password);
        UserLoginTokenModel saved = new UserLoginTokenModel();
        if (userModel != null) {
            UserLoginTokenModel userLoginTokenModel = new UserLoginTokenModel();
            userLoginTokenModel.setUser(userModel);
            userLoginTokenModel.setExpired("NO");
            userLoginTokenModel.setToken(UUID.randomUUID().toString());
            saved = userLoginTokenRepository.save(userLoginTokenModel);
        }
        return saved;
    }

    @Override
    public UserLoginTokenModel checkTokenStatus(String token) {
        return userLoginTokenRepository.findByToken(token);
    }

    @Override
    public BudgetModel getBudgetDetailsByCustomerCode(String token) {
        UserLoginTokenModel userLoginTokenModel = userLoginTokenRepository.findByToken(token);
        return userLoginTokenModel.getUser().getBudget();
    }

    @Override
    public List<TransactionModel> getTransactionListByCustomerCode(String token) {
        UserLoginTokenModel userLoginTokenModel = userLoginTokenRepository.findByToken(token);
        return userLoginTokenModel.getUser().getTransactions();
    }
}
