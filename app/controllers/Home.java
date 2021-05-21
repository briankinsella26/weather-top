package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Home extends Controller {
  public static void index() {
    Logger.info("Rendering Home");
    Member member = null;
    if(session.contains("logged_in_memberId")) {
      String memberId = session.get("logged_in_memberId");
      member = Member.findById(Long.parseLong(memberId));
    }

    render("home.html", member);
  }
}
