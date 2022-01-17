package ooga.DataTests;

import ooga.data.dataResources.DataConfig;
import ooga.data.ProfileHandler;
import ooga.data.dataExceptions.UserNotFoundException;
import ooga.data.dataExceptions.UnrecognizedValueException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

/**
 * test that the methods of the ProfileHandler class work correctly
 *
 * @author Keith cressman
 */
public class ProfileHandlerTests {

  /**
   * test that a profile can be added correctly
   * @return the username of the profile being added
   */
  @Test
  String testAddProfile(){
    Map<String, String> profileMap = new HashMap<>();
    Random r = new Random();
    int randInt = r.nextInt(999999);
    String username = String.format("Test%d", randInt);
    profileMap.put(DataConfig.USERNAME_KEY, username);
    profileMap.put(DataConfig.PASSWORD_KEY, "TestPassword");
    ProfileHandler.addProfile(profileMap);
    try {
      assertEquals(username, ProfileHandler.getPlayerInfo(username).get(DataConfig.USERNAME_KEY));
    } catch (Exception e){
      assertTrue(false);
    }
    return username;
  }

  /**
   * test that an exception is thrown when getPlayerInfo() is used with a username that doesn't exist
   */
  @Test
  void testGetPlayerInfoException() {
    assertThrows(UserNotFoundException.class, () -> ProfileHandler.getPlayerInfo("jlkasfdjla2341s"));
  }

  /**
   * test that the getPlayerInfo()  method works correctly with a valid username
   */
  @Test
  void testGetPlayerInfo(){
    try {
      Map<String, String> keithProfile = ProfileHandler.getPlayerInfo("Keith");
      assertEquals("Keith", keithProfile.get(DataConfig.USERNAME_KEY));
    } catch (Exception e){
      assertTrue(false);
    }
  }

  /**
   * test that the changeProfile() method can edit a profile
   */
  @Test
  void testChangeProfileVal(){
    String newWinCount = Integer.toString(new Random().nextInt(9999999));
    try {
      ProfileHandler.changeProfile("Keith", DataConfig.WINS_KEY, newWinCount);
      assertEquals(newWinCount, ProfileHandler.getPlayerInfo("Keith").get(DataConfig.WINS_KEY));
    } catch (Exception e){
      assertTrue(false);
    }

  }

  /**
   * test that the changeProfile method throws an exception if you try to change the info of a user
   * that doesn't exist
   */
  @Test
  void testChangeProfileValUserNotFound(){
    assertThrows(UserNotFoundException.class, () -> ProfileHandler.changeProfile("askjdfasd", DataConfig.WINS_KEY, "0"));
  }


  /**
   * test that the changeProfile method throws an exception if you try to change the value corresopnding to a key
   * which doesn't exist
   */
  @Test
  void testChangeProfileValBadKey(){
    assertThrows(UnrecognizedValueException.class, () -> ProfileHandler.changeProfile("Keith", "aslk;djf", "0"));
  }

  /**
   * test that the removeProfile method can correctly remove a profile
   */
  @Test
  void testRemoveProfile(){
    try {
      String newUser = testAddProfile();
      ProfileHandler.removeProfile(newUser);
      assertThrows(UserNotFoundException.class, () -> ProfileHandler.getPlayerInfo(newUser));

    } catch (Exception e){
      assertTrue(false);
    }
  }


  /**
   * test that the removeProfile method throws an exception if you try to remove a profile that doesn't exist
   */
  @Test
  void testRemoveProfileUserNotFound(){
    assertThrows(UserNotFoundException.class, () -> ProfileHandler.removeProfile("a;lsjdf"));
  }

}
