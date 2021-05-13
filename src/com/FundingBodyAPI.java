package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.FundingBody;

/**
 * Servlet implementation class FundingBodyAPI
 */
@WebServlet("/FundingBodyAPI")
public class FundingBodyAPI extends HttpServlet {
	//private static final long serialVersionUID = 1L;
	
	FundingBody FundingBodyObj = new FundingBody();
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundingBodyAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = FundingBodyObj.insertFundingBody(
				request.getParameter("FundingBodyID"),
				request.getParameter("Name"),
				request.getParameter("Telno"),
				request.getParameter("CompanyName"),
				request.getParameter("CompanyAddress"),
				request.getParameter("Email")
				
				);
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = FundingBodyObj.updateFundingBody(
		paras.get("hiddenFundingBodyIDSave").toString(),
		paras.get("Name").toString(),
		paras.get("Telno").toString(),
		paras.get("CompanyName").toString(),
		paras.get("CompanyAddress").toString(),
		paras.get("Email").toString());
		response.getWriter().write(output);
		}
		
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		 String output = FundingBodyObj.deleteFundingBody(paras.get("FundingBodyID").toString());
		response.getWriter().write(output); 
	}
	

	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}

	

}