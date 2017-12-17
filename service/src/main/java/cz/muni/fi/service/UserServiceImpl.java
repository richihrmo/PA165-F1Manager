package cz.muni.fi.service;

import cz.muni.fi.dao.UserDao;
import cz.muni.fi.entities.User;
import cz.muni.fi.service.exception.ServiceDataAccessException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;

/**
 * @author Robert Tamas
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public Collection<User> findAllUsers() {
        try {
            return userDao.findAllUsers();
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot list all users", e);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("Email cannot be null");
        try {
            return userDao.findUserByEmail(email);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot find user by email", e);
        }
    }

    @Override
    public User findUserById(Long id) {
        if (id == null) throw new IllegalArgumentException("Id cannot be null");
        try {
            return userDao.findUserById(id);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot find user by id", e);
        }
    }

    @Override
    public void deleteUser(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        try {
            userDao.deleteUser(user);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot delete user", e);
        }
    }

    @Override
    public User updateUser(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        try {
            return userDao.updateUser(user);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot update user", e);
        }
    }

    @Override
    public User addUser(User user, String password) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");
        try {
            user.setPasswordHash(createHash(password));
            return userDao.addUser(user);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot add user", e);
        }
    }

    @Override
    public boolean authenticate(User user, String password) {
        if (user == null) {
            throw new IllegalArgumentException("User is null!");
        }
        // Fresh data

        User userResult = findUserById(user.getId());

        return validatePassword(password, userResult.getPasswordHash());
    }

    //see  https://crackstation.net/hashing-security.htm#javasourcecode
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }

    public static boolean validatePassword(String password, String correctHash) {
        if (password == null) return false;
        if (correctHash == null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }
}
