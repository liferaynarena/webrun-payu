package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class sucess_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


 public String hashCal(String type,String str){
    byte[] hashseq=str.getBytes();
    StringBuffer hexString = new StringBuffer();
    try{
    MessageDigest algorithm = MessageDigest.getInstance(type);
    algorithm.reset();
    algorithm.update(hashseq);
    byte messageDigest[] = algorithm.digest();



    for (int i=0;i<messageDigest.length;i++) {
        String hex=Integer.toHexString(0xFF & messageDigest[i]);
        if(hex.length()==1) hexString.append("0");
        hexString.append(hex);
    }

    }catch(NoSuchAlgorithmException nsae){ }

    return hexString.toString();


}

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<body>\r\n");

String amount = request.getParameter("amount");
String productinfo= request.getParameter("productinfo");
String txnid = request.getParameter("txnid");
String phone = request.getParameter("phone");
String firstname = request.getParameter("firstname");
String key = "JBZaLc";
String salt = "GQs7yium";
String status = request.getParameter("status");
String r_h =request.getParameter("hash");
String hashString="";
String udf1 =request.getParameter("udf1");
String udf2 =request.getParameter("udf2");
String udf3 =request.getParameter("udf3");
String udf4 =request.getParameter("udf4");
String udf5 =request.getParameter("udf5");
String p_Id = request.getParameter("payuMoneyId");
String additionalCharges = request.getParameter("additionalCharges");
out.println("Your paymnet with Payment ID is :" + p_Id + "is ");

 String hash;
 String email = request.getParameter("email");
 if(status.equals("success")){
 if(additionalCharges!=null){
 String hashSequence = additionalCharges+"|"+salt+"|"+status+"||||||"+udf5+"|"+udf4+"|"+udf3+"|"+udf2+"|"+udf1+"|"+email+"|"+firstname+"|"+productinfo+"|"+amount+"|"+txnid+"|";


  hashString=hashSequence.concat(key);
  out.println(hashString);
  hash=hashCal("SHA-512",hashString);
  out.println(hash);
  if(r_h.equals(hash)){
  out.println("Successfull with additiona charges");
  out.println("Transaction details:");
  out.println("Amount:"+amount);
  out.println("additionalCharges:"+additionalCharges);
  out.println("Status of Transaction:"+status);}

  else {
  out.println("Transaction details:");
  out.println("Amount:"+amount);
  out.println("additionalCharges:"+additionalCharges);
  out.println("Status of Transaction:"+status);
                    }
            }
            else {
            String hashSequence = salt+"|"+status+"||||||"+udf5+"|"+udf4+"|"+udf3+"|"+udf2+"|"+udf1+"|"+email+"|"+firstname+"|"+productinfo+"|"+amount+"|"+txnid+"|";


        hashString=hashSequence.concat(key);
                    out.println(hashString);
                    hash=hashCal("SHA-512",hashString);
                    out.println(hash);
        if(r_h.equals(hash)){
             out.println("Successfull");
                    out.println("Transaction details:");
                        out.println("Amount:"+amount);
        out.println("additionalCharges:"+additionalCharges);
        out.println("Status of Transaction:"+status);}

        else{ out.println("failure");
                    out.println("Transaction details:");
                        out.println("Amount:"+amount);
       out.println("additionalCharges:"+additionalCharges);
                        out.println("Status of Transaction:"+status);}
            }   
    }else {out.println("Transaction details:");
                        out.println("Amount:"+amount);
out.println("additionalCharges:"+additionalCharges);
                        out.println("Status of Transaction:"+status);
                    }

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
