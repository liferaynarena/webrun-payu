package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.security.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


public boolean empty(String s)
	{
		if(s== null || s.trim().equals(""))
			return true;
		else
			return false;
	}


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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write('\n');
      out.write('\n');
 	
	String merchant_key="gtKFFx";
	String salt="eCwWELxi";
	String action1 ="";
	String base_url="https://test.payu.in";
	int error=0;
	String hashString="";
	
 
	
	Enumeration paramNames = request.getParameterNames();
	Map<String,String> params= new HashMap<String,String>();
    	while(paramNames.hasMoreElements()) 
	{
      		String paramName = (String)paramNames.nextElement();
      
      		String paramValue = request.getParameter(paramName);
		params.put(paramName,paramValue);
	}
	String txnid ="";
	if(empty(params.get("txnid"))){
		Random rand = new Random();
		String rndm = Integer.toString(rand.nextInt())+(System.currentTimeMillis() / 1000L);
		txnid=hashCal("SHA-256",rndm).substring(0,20);
	}
	else
		txnid=params.get("txnid");
	String txn="abcd";
	String hash="";
	String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
	if(empty(params.get("hash")) && params.size()>0)
	{
		if( empty(params.get("key"))
			|| empty(params.get("txnid"))
			|| empty(params.get("amount"))
			|| empty(params.get("firstname"))
			|| empty(params.get("email"))
			|| empty(params.get("phone"))
			|| empty(params.get("productinfo"))
			|| empty(params.get("surl"))
			|| empty(params.get("furl"))	)
			
			error=1;
		else{
			String[] hashVarSeq=hashSequence.split("\\|");
			
			for(String part : hashVarSeq)
			{
				hashString= (empty(params.get(part)))?hashString.concat(""):hashString.concat(params.get(part));
				hashString=hashString.concat("|");
			}
			hashString=hashString.concat(salt);
			
			 hash=hashCal("SHA-512",hashString);
			action1=base_url.concat("/_payment");
		}
	}
	else if(!empty(params.get("hash")))
	{
		hash=params.get("hash");
		action1=base_url.concat("/_payment");
	}
		

      out.write("\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("var hash='");
      out.print( hash );
      out.write("';\n");
      out.write("function submitPayuForm() {\n");
      out.write("\t\n");
      out.write("\tif (hash == '')\n");
      out.write("\t\treturn;\n");
      out.write("      var payuForm = document.forms.payuForm;\n");
      out.write("      payuForm.submit();\n");
      out.write("    }\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<body onload=\"submitPayuForm();\">\n");
      out.write("\n");
      out.write("\n");
      out.write("<form action=\"");
      out.print( action1 );
      out.write("\" method=\"post\" name=\"payuForm\">\n");
      out.write("<input type=\"hidden\" name=\"key\" value=\"");
      out.print( merchant_key );
      out.write("\" />\n");
      out.write("      <input type=\"hidden\" name=\"hash\" value=\"");
      out.print( hash );
      out.write("\"/>\n");
      out.write("      <input type=\"hidden\" name=\"txnid\" value=\"");
      out.print( txnid );
      out.write("\" />\n");
      out.write("      <table>\n");
      out.write("        <tr>\n");
      out.write("          <td><b>Mandatory Parameters</b></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>Amount: </td>\n");
      out.write("          <td><input name=\"amount\" value=\"");
      out.print( (empty(params.get("amount"))) ? "" : params.get("amount") );
      out.write("\" /></td>\n");
      out.write("          <td>First Name: </td>\n");
      out.write("          <td><input name=\"firstname\" id=\"firstname\" value=\"");
      out.print( (empty(params.get("firstname"))) ? "" : params.get("firstname") );
      out.write("\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>Email: </td>\n");
      out.write("          <td><input name=\"email\" id=\"email\" value=\"");
      out.print( (empty(params.get("email"))) ? "" : params.get("email") );
      out.write("\" /></td>\n");
      out.write("          <td>Phone: </td>\n");
      out.write("          <td><input name=\"phone\" value=\"");
      out.print( (empty(params.get("phone"))) ? "" : params.get("phone") );
      out.write("\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>Product Info: </td>\n");
      out.write("          <td colspan=\"3\"><input name=\"productinfo\" value=\"");
      out.print( (empty(params.get("productinfo"))) ? "" : params.get("productinfo") );
      out.write("\" size=\"64\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>Success URI: </td>\n");
      out.write("          <td colspan=\"3\"><input name=\"surl\" value=\"http://localhost:8080/sucess.jsp\" size=\"64\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>Failure URI: </td>\n");
      out.write("          <td colspan=\"3\"><input name=\"furl\" value=\"");
      out.print( (empty(params.get("furl"))) ? "" : params.get("furl") );
      out.write("\" size=\"64\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td><b>Optional Parameters</b></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>Last Name: </td>\n");
      out.write("          <td><input name=\"lastname\" id=\"lastname\" value=\"");
      out.print( (empty(params.get("lastname"))) ? "" : params.get("lastname") );
      out.write("\" /></td>\n");
      out.write("          <td>Cancel URI: </td>\n");
      out.write("          <td><input name=\"curl\" value=\"\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>Address1: </td>\n");
      out.write("          <td><input name=\"address1\" value=\"");
      out.print( (empty(params.get("address1"))) ? "" : params.get("address1") );
      out.write("\" /></td>\n");
      out.write("          <td>Address2: </td>\n");
      out.write("          <td><input name=\"address2\" value=\"");
      out.print( (empty(params.get("address2"))) ? "" : params.get("address2") );
      out.write("\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>City: </td>\n");
      out.write("          <td><input name=\"city\" value=\"");
      out.print( (empty(params.get("city"))) ? "" : params.get("city") );
      out.write("\" /></td>\n");
      out.write("          <td>State: </td>\n");
      out.write("          <td><input name=\"state\" value=\"");
      out.print( (empty(params.get("state"))) ? "" : params.get("state") );
      out.write("\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>Country: </td>\n");
      out.write("          <td><input name=\"country\" value=\"");
      out.print( (empty(params.get("country"))) ? "" : params.get("country") );
      out.write("\" /></td>\n");
      out.write("          <td>Zipcode: </td>\n");
      out.write("          <td><input name=\"zipcode\" value=\"");
      out.print( (empty(params.get("zipcode"))) ? "" : params.get("zipcode") );
      out.write("\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>UDF1: </td>\n");
      out.write("          <td><input name=\"udf1\" value=\"");
      out.print( (empty(params.get("udf1"))) ? "" : params.get("udf1") );
      out.write("\" /></td>\n");
      out.write("          <td>UDF2: </td>\n");
      out.write("          <td><input name=\"udf2\" value=\"");
      out.print( (empty(params.get("udf2"))) ? "" : params.get("udf2") );
      out.write("\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>UDF3: </td>\n");
      out.write("          <td><input name=\"udf3\" value=\"");
      out.print( (empty(params.get("udf3"))) ? "" : params.get("udf3") );
      out.write("\" /></td>\n");
      out.write("          <td>UDF4: </td>\n");
      out.write("          <td><input name=\"udf4\" value=\"");
      out.print( (empty(params.get("udf4"))) ? "" : params.get("udf4") );
      out.write("\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          <td>UDF5: </td>\n");
      out.write("          <td><input name=\"udf5\" value=\"");
      out.print( (empty(params.get("udf5"))) ? "" : params.get("udf5") );
      out.write("\" /></td>\n");
      out.write("          <td>PG: </td>\n");
      out.write("          <td><input name=\"pg\" value=\"");
      out.print( (empty(params.get("pg"))) ? "" : params.get("pg") );
      out.write("\" /></td>\n");
      out.write("        </tr>\n");
      out.write("        <tr>\n");
      out.write("          ");
 if(empty(hash)){ 
      out.write("\n");
      out.write("            <td colspan=\"4\"><input type=\"submit\" value=\"Submit\" /></td>\n");
      out.write("          ");
 } 
      out.write("\n");
      out.write("        </tr>\n");
      out.write("      </table>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
