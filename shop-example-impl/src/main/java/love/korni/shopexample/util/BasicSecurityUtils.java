package love.korni.shopexample.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * BasicSecurityUtils
 *
 * @author Sergei_Konilov
 */
@UtilityClass
public class BasicSecurityUtils {

    public User getUserFromContext() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
