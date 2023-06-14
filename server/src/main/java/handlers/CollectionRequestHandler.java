package handlers;

import exceptions.ServerException;
import models.User;
import network.CollectionRequest;
import network.CollectionResponse;
import services.UserService;
import views.*;

import java.net.Socket;
import java.util.HashMap;

public class CollectionRequestHandler extends RequestHandler {
    private final HashMap<String, View> views = new HashMap<>() {{
        put("login", new LoginView());
        put("register", new RegisterView());
        put("info", new InfoView());
        put("show", new ShowView());
        put("add", new AddView());
        put("update", new UpdateView());
        put("remove_by_id", new RemoveByIdView());
        put("clear", new ClearView());
        put("remove_greater", new RemoveGreaterView());
        put("remove_lower", new RemoveLowerView());
        put("filter_by_group_admin", new FilterByGroupAdminView());
        put("filter_contains_name", new FilterContainsNameView());
        put("print_unique_student_count", new UniqueStudentCountView());
    }};
    private final UserService userService = UserService.getInstance();

    public CollectionRequestHandler(Socket socket) {
        super(socket);
    }

    /** Генерирует ответ на запрос через View, соответствующий команде из запроса.
     * Если учетные данные неверны или не были предоставлены, возвращает ответ с ошибкой,
     * иначе устанавливает id пользователю-отправителю запроса, для корректной обработки View. */
    protected CollectionResponse createResponse(CollectionRequest request) {
        View view = views.get(request.getCommand());
        if (view == null)
            return new CollectionResponse(false, "Unknown command.");
        if (request.getUser() == null)
            return new CollectionResponse(false, "You are not authorized.");
        if (!request.getCommand().equals("login") && !request.getCommand().equals("register")) {
            try {
                User user = userService.login(request.getUser());
                request.setUser(user);
            } catch (ServerException e) {
                return new CollectionResponse(false, "Bad credentials.");
            }
        }
        return view.generateResponse(request);
    }
}
