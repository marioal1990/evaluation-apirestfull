package cl.api.apiuser.util;

import cl.api.apiuser.bean.model.Phone;
import cl.api.apiuser.bean.model.User;
import cl.api.apiuser.bean.request.RegistroControllerRequest;
import cl.api.apiuser.bean.dto.PhoneDTO;
import cl.api.apiuser.bean.response.UserControllerResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParseUtil {

    private ParseUtil() {}

    /**
     * Parsea el objeto usuario con los datos ingresados desde el objeto controlador de petición (ControllerRequest.java)
     * @param registroControllerRequest Objeto controlador de petición del método registro
     * @return Objeto entida Usuario
     */
    public static User registroControllerRequestToUser(RegistroControllerRequest registroControllerRequest) {
        User user = new User();
        user.setName(registroControllerRequest.getName());
        user.setEmail(registroControllerRequest.getEmail());
        user.setPassword(registroControllerRequest.getPassword());
        user.setCreated(DateUtil.dateToString(new Date()));
        user.setModified(DateUtil.dateToString(new Date()));
        user.setLastLogin(DateUtil.dateToString(new Date()));
        user.setPhones(phoneDTOToPhone(registroControllerRequest.getPhones()));
        user.setActive(true);
        return user;
    }

    /**
     * Parsea la lista de objeto DTO de teléfono en la entidad teléfono
     * @param dtos Objeto DTO que representa los telefonos del usuario a registrar
     * @return Lista de objetos entidad telefono
     */
    public static List<Phone> phoneDTOToPhone(List<PhoneDTO> dtos) {
        List<Phone> phones = new ArrayList<>();
        dtos.stream().map(dto -> {
            Phone phone = new Phone();
            phone.setNumber(Long.parseLong(dto.getNumber()));
            phone.setCityCode(dto.getCityCode());
            phone.setContryCode(dto.getContryCode());
            return phone;
        }).forEach(phones::add);
        return phones;
    }

    /**
     * Parsea el objeto {@code User} a {@code UserControllerResponse}
     * @param user Objeto Usuario en el que se usará para parsearlo al objeto {@code UserControllerResponse}
     * @return {@code UserControllerResponse} con datos cargados desde un objeto {@code User}
     */
    public static UserControllerResponse userToUserControllerResponse(User user) {
        UserControllerResponse userControllerResponse = new UserControllerResponse();
        userControllerResponse.setUuid(user.getUuid());
        userControllerResponse.setCreated(user.getCreated());
        userControllerResponse.setModified(user.getModified());
        userControllerResponse.setLastLogin(user.getLastLogin());
        userControllerResponse.setActive(user.isActive());
        return userControllerResponse;
    }
}
