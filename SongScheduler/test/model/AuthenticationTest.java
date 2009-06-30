/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author liufeng
 */
public class AuthenticationTest {

    public AuthenticationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of init method, of class Authentication.
     */
    @Test
    @Ignore
    public void testInit() {
        System.out.println("init");
        Authentication instance = new AuthenticationImpl();
        instance.init();
    }

    /**
     * Test of isAuthenticated method, of class Authentication.
     */
    @Test
    public void testIsAuthenticated() {
        System.out.println("isAuthenticated");
        Authentication instance = new AuthenticationImpl();
        instance.init();
        boolean expResult = false;
        boolean result = instance.isAuthenticated();
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPassword method, of class Authentication.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        String word = "password";
        Authentication instance = new AuthenticationImpl();
        instance.init();
        boolean expResult = false;
        boolean result = instance.checkPassword(word);
        assertEquals(expResult, result);
        word = "";
        expResult = true;
        result = instance.checkPassword(word);
        assertEquals(expResult, result);
    }

    /**
     * Test of modifyPassword method, of class Authentication.
     */
    @Test
    public void testModifyPassword() {
        System.out.println("modifyPassword");
        String newPass = "new_password";
        Authentication instance = new AuthenticationImpl();
        instance.init();
        boolean expResult = false;
        boolean result = instance.modifyPassword(newPass);
        assertEquals(expResult, result);
        instance.checkPassword("");
        expResult = true;
        result = instance.modifyPassword(newPass);
        assertEquals(expResult, result);
    }

    public class AuthenticationImpl extends Authentication {
    }

}