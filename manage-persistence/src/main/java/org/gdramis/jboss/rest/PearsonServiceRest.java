package org.gdramis.jboss.rest;

import javax.validation.Validator;

import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.gdramis.jboss.model.Pearson;
import org.gdramis.jboss.service.PearsonService;

@Stateless
@Path("manage")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"admins", "users"})	
public class PearsonServiceRest {
	
	@Inject
	PearsonService pearsonService;
	
	@GET
	@Path("healtcheck")
	public Response getHealthCheck() {
		Response.ResponseBuilder builder = Response.ok();
		return builder.build();
	}
	
	@GET
	@Path("pearsons")
	@RolesAllowed({"admins", "users"})	
	public Response getAllPearsons() {
		Response.ResponseBuilder builder = null;
		try {
			builder = Response.ok(pearsonService.listAll());
			return builder.build();
		} 
		catch (Exception e) {
			builder = Response.serverError();
			return builder.build();
		}
	}
	
	@POST
	@Path("pearsons")
	@RolesAllowed({"admins"})	
	public Response setPearson(Pearson pearson) {
		Response.ResponseBuilder builder = null;
		try {
			this.validationFunction(pearson);
			List<Pearson> pearsons = pearsonService.findByMail(pearson.getEmail());
			if (pearsons.size() == 0) {
				pearsonService.registerItem(pearson);
				builder = Response.ok();
			} else 
				builder = Response.status(Status.CONFLICT);
			return builder.build();
		} catch (ConstraintViolationException e) {
			builder = Response.status(Status.BAD_REQUEST);	
			return builder.build();
		} 
		catch (Exception e) {
			builder = Response.serverError();
			return builder.build();
		}
	}
	
	@GET
	@Path("pearsons/{id}")
	public Response getPearson(@PathParam("id") Long id) {
		Response.ResponseBuilder builder = null;
		try {
			builder = Response.ok(pearsonService.findById(id));
			return builder.build();
		} catch (Exception e) {
			builder = Response.serverError();
			return builder.build();
		}
	}
	
	@DELETE
	@Path("pearsons/{id}")
	@RolesAllowed({"admins"})	
	public Response deletePearson(@PathParam("id") Long id) {
		Response.ResponseBuilder builder = null;
		try {
			builder = Response.ok();
			pearsonService.removeItem(id);
			return builder.build();
		} catch (Exception e) {
			builder = Response.serverError();
			return builder.build();
		}
	}

	@GET
	@Path("pearsons/getBySurname")
	@RolesAllowed({"admins", "users"})	
	public Response getAllPearsonsBySurame(@QueryParam("surname") String surname) {
		Response.ResponseBuilder builder = null;
		try {
			builder = Response.ok(pearsonService.listBySurname(surname));
			return builder.build();
		} 
		catch (Exception e) {
			builder = Response.serverError();
			return builder.build();
		}
	}
	
	@GET
	@Path("pearsons/getByName")
	@RolesAllowed({"admins", "users"})	
	public Response getAllPearsonsByName(@QueryParam("name") String name) {
		Response.ResponseBuilder builder = null;
		try {
			builder = Response.ok(pearsonService.listByName(name));
			return builder.build();
		} 
		catch (Exception e) {
			builder = Response.serverError();
			return builder.build();
		}
	}
	
	private void validationFunction(Pearson pearson) throws Exception {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		Set<ConstraintViolation<Pearson>> constraintViolations = validator.validate(pearson);
		if (constraintViolations.size() > 0)
			throw new ConstraintViolationException(constraintViolations);
	}
}
