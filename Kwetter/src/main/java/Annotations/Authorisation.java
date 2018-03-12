package Annotations;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


public class Authorisation {

    @AroundInvoke
    public Object intercept(InvocationContext context)throws Exception {
        Object result = context.proceed();
        return result;
    }
}
