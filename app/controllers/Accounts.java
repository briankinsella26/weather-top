package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {

  public static void index() {
    Member member = Accounts.getLoggedInMember();
    Logger.info("Member = " + member.email);

    render("profile.html", member);
  }

  public static void signup() {
    render("signup.html");
  }

  public static void login() {
    render("login.html");
  }

  public static void register(String firstName, String lastName, String email, String password) {
    Logger.info("Register user: " + email);
    Member member = new Member(firstName, lastName, email, password);
    member.save();
    session.put("logged_in_memberId", member.id);

    redirect("/dashboard");
  }

  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate user: " + email);
    Member member = Member.findByEmail(email);
    if ((member != null) && (member.checkPassword(password))) {
      Logger.info("Authentication successful");
      session.put("logged_in_memberId", member.id);

      redirect("/dashboard");
    } else {
      Logger.info("Authentication failed");

      redirect("/login");
    }
  }

  public static void logout() {
    session.clear();

    redirect("/");
  }

  public static Member getLoggedInMember() {
    Member member = null;
    if (session.contains("logged_in_memberId")) {
      String memberId = session.get("logged_in_memberId");
      member = Member.findById(Long.parseLong(memberId));
    } else {
      login();
    }
    return member;
  }

  public static void editProfile(String firstName, String lastName, String email, String password) {
    Logger.info("Editing user: " + email);
    Member member = getLoggedInMember();
    member.firstName = firstName;
    member.lastName = lastName;
    member.password = password;
    member.email = email;
    member.save();

    redirect("/dashboard");
  }

  public static void editProfile(String firstName, String lastName, String email) {
    Logger.info("Editing user: " + email);
    Member member = getLoggedInMember();
    member.firstName = firstName;
    member.lastName = lastName;
    member.email = email;
    member.save();

    redirect("/dashboard");
  }
}
