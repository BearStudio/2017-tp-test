package fr.rouen.mastergil.user;

/**
 * Created by rudy on 05/03/17.
 */
public interface PasswordEncoder {
    String encode(CharSequence var1);

    boolean matches(CharSequence var1, String var2);
}