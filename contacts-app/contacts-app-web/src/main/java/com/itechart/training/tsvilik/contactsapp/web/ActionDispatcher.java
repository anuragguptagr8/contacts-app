package com.itechart.training.tsvilik.contactsapp.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.itechart.training.tsvilik.contactsapp.web.routing.Route;
import com.itechart.training.tsvilik.contactsapp.web.routing.RoutingInfo;

public class ActionDispatcher {

	private static Logger logger = Logger.getLogger(ActionDispatcher.class);

	private static RoutingInfo routingInfo;
	private static List<Route> routes;

	static {
		routingInfo = RoutingInfo.load();
		routes = routingInfo.getRoutes();
	}

	private static Route getRequestedRoute(HttpServletRequest request) {
		String path;
		if (request.getServletPath() == null
				|| request.getServletPath().isEmpty()) {
			path = "/";
		} else {
			path = request.getServletPath();
		}
		Route requestedRoute = null;
		for (int i = 0; i < routes.size(); i++) {
//			if (path.equalsIgnoreCase(routes.get(i).getPath())) {
			if (path.matches("^" + routes.get(i).getPath() + "/?$")) {
				requestedRoute = routes.get(i);
				break;
			}
		}
		if (requestedRoute == null) {
			logger.debug("The path " + path + " hasn't been found.");
		}
		return requestedRoute;
	}

	private static ActionResult invokeRouteAction(Route route,
			HttpServletRequest request) {
		ActionResult actionResult = null;
		String controllerClassName = routingInfo.getControllerPackage() + "."
				+ route.getController() + "Controller";
		try {
			Class controllerClass = Class.forName(controllerClassName);
			Object controller = controllerClass.newInstance();
			Method action = controllerClass.getMethod(route.getAction(),
					HttpServletRequest.class);
			actionResult = (ActionResult) action.invoke(controller, request);
		} catch (ClassNotFoundException e) {
			logger.info("Controller class hasn't been found: "
					+ controllerClassName, e);
		} catch (NoSuchMethodException e) {
			logger.info("The action '" + route.getAction()
					+ "' hasn't been found. Controller " + controllerClassName,
					e);
		} catch (SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException e) {
			logger.info(e);
		} catch (InvocationTargetException e) {
			logger.error("The action '" + route.getAction()
					+ "' from controller " + controllerClassName
					+ " throwed an exception.", e);
		}
		return actionResult;
	}

	public static ActionResult dispatch(HttpServletRequest request) {
		Route requestedRoute = getRequestedRoute(request);
		if (requestedRoute != null) {
			ActionResult actionResult = invokeRouteAction(requestedRoute,
					request);
			if (actionResult != null) {
				return actionResult;
			}
		}
		return new NotFoundResult(request);
	}

}