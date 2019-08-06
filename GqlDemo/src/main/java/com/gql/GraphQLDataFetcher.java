package com.gql;

import org.hibernate.Session;

import com.hb.HibernateUtil;
import com.hb.entity.Employee;

import graphql.schema.DataFetcher;

public class GraphQLDataFetcher {
	public DataFetcher getEmployeeByIdDataFetcher() {

		return dataFetchingEnvironment -> {
			Session session = null;
			Employee employee = null;
			try {
				session = HibernateUtil.getSessionFactory().openSession();
				Long id = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
				employee = (Employee) session.get(Employee.class, id);
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return employee;
		};
	}

}
