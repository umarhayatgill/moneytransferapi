import binding.BasicModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import controller.AccountController;
import controller.UserController;
import helper.ResponseError;
import service.AccountServiceImpl;
import service.UserService;
import service.UserServiceImpl;

import static helper.JsonUtil.toJson;
import static spark.Spark.*;

public class MoneyTransferAPI {
    public static void main(String[] args) {
        after((req, res) -> {
            res.type("application/json");
        });
        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });
        Injector injector = Guice.createInjector(new BasicModule());
        UserService userService = injector.getInstance(UserService.class);
        new UserController(userService);
        new AccountController(new AccountServiceImpl());
    }
}