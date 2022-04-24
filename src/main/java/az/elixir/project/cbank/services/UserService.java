package az.elixir.project.cbank.services;

import az.elixir.project.cbank.dto.UserRegisterRequest;
import az.elixir.project.cbank.models.BudgetModel;
import az.elixir.project.cbank.models.TransactionModel;
import az.elixir.project.cbank.models.UserLoginTokenModel;
import az.elixir.project.cbank.models.UserModel;

import java.util.List;

public interface UserService {

    void registerUser(UserRegisterRequest userRegisterRequest);
    UserModel getCustomerDataByCustomerCode(String token);
    UserLoginTokenModel login(String email, String password);
    UserLoginTokenModel checkTokenStatus(String token);
    BudgetModel getBudgetDetailsByCustomerCode(String token);
    List<TransactionModel> getTransactionListByCustomerCode(String token);


}
