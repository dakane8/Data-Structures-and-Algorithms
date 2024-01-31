package assn07;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "YOUR PASSWORD HERE";
    private Account[] _passwords;
    private int _size;
    private HashSet<K> _set;

    public PasswordManager() {
        _passwords = new Account[50];
        this._size = 0;
        this._set = new HashSet<>();
    }


    // TODO: put
    @Override
    public void put(K key, V value) {
        int idx = Math.abs(key.hashCode()) % this._passwords.length;
        Account<K, V> curr = new Account<K, V>(key, value);
        if (this._passwords[idx] != null) {
            Account<K, V> prev = this._passwords[idx];
            curr.setNext(prev);
            while (prev != null) {
                if (prev.getWebsite().equals(key)) {
                    prev.setPassword(value);
                    return;
                }
                prev = prev.getNext();
            }
        }
        this._set.add(key);
        this._passwords[idx] = curr;
        this._size++;
    }

    // TODO: get
    @Override
    public V get(K key) {
        int idx = Math.abs(key.hashCode()) % this._passwords.length;
        Account<K, V> prev = this._passwords[idx];
        while (prev != null) {
            if (prev.getWebsite().equals(key)) {
                return prev.getPassword();
            }
            prev = prev.getNext();
        }
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        return this._size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        return this._set;
    }

    // TODO: remove
    @Override
    public V remove(K key) {
        int idx = Math.abs(key.hashCode()) % this._passwords.length;
        Account<K, V> prev = null;
        Account<K, V> curr = this._passwords[idx];
        while (curr != null) {
            if (curr.getWebsite().equals(key)) {
                V password = curr.getPassword();
                if (prev != null) {
                    prev.setNext(curr.getNext());
                } else {
                    this._passwords[idx] = null;
                }
                this._set.remove(key);
                this._size--;
                return password;
            }
            prev = curr;
            curr = curr.getNext();
        }
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> dupes = new ArrayList<>();

        for (int i = 0; i < this._passwords.length; i++) {
            Account<K, V> curr = this._passwords[i];

            while (curr != null) {
                if (curr.getPassword().equals(value)) {
                    dupes.add(curr.getWebsite());
                }
                curr = curr.getNext();
            }
        }

        return dupes;    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return enteredPassword.equals(MASTER_PASSWORD);
    }
    
    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
