/**
 * 
 */
package org.test.dramis.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.test.dramis.bean.Magic8BallBean;

/**
 * @author gdramis
 * 
 */
@Path("eightBallService")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EightBallService {
	
	@EJB
	Magic8BallBean eightBallBean;
	
	@GET
	@Path("askquestion")
	public String getEightBallService(@QueryParam("question") String question) {
		return eightBallBean.answerQuestion(question);
	}
}
