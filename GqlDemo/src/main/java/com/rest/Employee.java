package com.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gql.GraphQLProvider;
import com.pojo.QlRequest;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;

@Path("/employee")
public class Employee {
	private GraphQL graphQL;

	public Employee() throws Exception {
		GraphQLProvider gpl = new GraphQLProvider();
		graphQL = gpl.graphQL();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public String query(final InputStream incomingData) throws Exception {
		String reqJson = getIncomingJson(incomingData);
		QlRequest reqObj = new Gson().fromJson(reqJson, QlRequest.class);
		ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(reqObj.getQuery()).build();
		ExecutionResult executionResult = graphQL.execute(executionInput);
		GsonBuilder gsonMapBuilder = new GsonBuilder(); 
		Gson gson = gsonMapBuilder.create();
		return gson.toJson(executionResult);
	
	}

	protected String getIncomingJson(InputStream incoming) throws Exception {
		StringBuilder input = new StringBuilder();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(incoming, "UTF-8"));
			String line = null;
			while ((line = in.readLine()) != null) {
				input.append(line);
			}
		} finally {
			if (in != null)
				in.close();
		}
		return input.toString();
	}

}
