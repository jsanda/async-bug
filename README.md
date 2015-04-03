Steps to produce async request issue
===
1. mvn package
2. cp target/async-bug-0.1.0.war /path/to/wildfly-9.0.0.Beta2/standalone/deployments
3. /path/to/wildfly-9.0.0.Beta2/bin/standalone.sh
4. ./post_data
5. After a short time the following error occurrs

<pre>
18:08:04,509 ERROR [stderr] (pool-12-thread-4) Exception in thread "pool-12-thread-4" java.util.ConcurrentModificationException
18:08:04,509 ERROR [stderr] (pool-12-thread-4) 	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1429)
18:08:04,510 ERROR [stderr] (pool-12-thread-4) 	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
18:08:04,510 ERROR [stderr] (pool-12-thread-4) 	at io.undertow.servlet.util.IteratorEnumeration.nextElement(IteratorEnumeration.java:44)
18:08:04,510 ERROR [stderr] (pool-12-thread-4) 	at org.jboss.weld.util.collections.EnumerationList.<init>(EnumerationList.java:42)
18:08:04,510 ERROR [stderr] (pool-12-thread-4) 	at org.jboss.weld.context.beanstore.http.RequestBeanStore.getAttributeNames(RequestBeanStore.java:48)
18:08:04,510 ERROR [stderr] (pool-12-thread-4) 	at org.jboss.weld.context.beanstore.AttributeBeanStore.getPrefixedAttributeNames(AttributeBeanStore.java:198)
18:08:04,510 ERROR [stderr] (pool-12-thread-4) 	at org.jboss.weld.context.beanstore.AttributeBeanStore.attach(AttributeBeanStore.java:95)
18:08:04,511 ERROR [stderr] (pool-12-thread-4) 	at org.jboss.weld.context.http.HttpRequestContextImpl.associate(HttpRequestContextImpl.java:58)
18:08:04,511 ERROR [stderr] (pool-12-thread-4) 	at org.jboss.weld.context.http.HttpRequestContextImpl.associate(HttpRequestContextImpl.java:38)
18:08:04,511 ERROR [stderr] (pool-12-thread-4) 	at org.jboss.weld.servlet.HttpContextLifecycle.requestInitialized(HttpContextLifecycle.java:216)
18:08:04,511 ERROR [stderr] (pool-12-thread-4) 	at org.jboss.weld.servlet.WeldInitialListener.requestInitialized(WeldInitialListener.java:160)
18:08:04,511 ERROR [stderr] (pool-12-thread-4) 	at io.undertow.servlet.core.ApplicationListeners.requestInitialized(ApplicationListeners.java:216)
18:08:04,511 ERROR [stderr] (pool-12-thread-4) 	at io.undertow.servlet.spec.AsyncContextImpl.setupRequestContext(AsyncContextImpl.java:687)
18:08:04,512 ERROR [stderr] (pool-12-thread-4) 	at io.undertow.servlet.spec.AsyncContextImpl.onAsyncComplete(AsyncContextImpl.java:597)
18:08:04,512 ERROR [stderr] (pool-12-thread-4) 	at io.undertow.servlet.spec.AsyncContextImpl.complete(AsyncContextImpl.java:275)
18:08:04,512 ERROR [stderr] (pool-12-thread-4) 	at org.jboss.resteasy.plugins.server.servlet.Servlet3AsyncHttpRequest$Servlet3ExecutionContext$Servle3AsychronousResponse.resume(Servlet3AsyncHttpRequest.java:97)
18:08:04,512 ERROR [stderr] (pool-12-thread-4) 	at async.AsyncHandler$1.onSuccess(AsyncHandler.java:64)
18:08:04,512 ERROR [stderr] (pool-12-thread-4) 	at async.AsyncHandler$1.onSuccess(AsyncHandler.java:61)
18:08:04,512 ERROR [stderr] (pool-12-thread-4) 	at com.google.common.util.concurrent.Futures$4.run(Futures.java:1181)
18:08:04,513 ERROR [stderr] (pool-12-thread-4) 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
18:08:04,513 ERROR [stderr] (pool-12-thread-4) 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
18:08:04,513 ERROR [stderr] (pool-12-thread-4) 	at java.lang.Thread.run(Thread.java:745)
18:08:04,665 ERROR [stderr] (pool-12-thread-2) Exception in thread "pool-12-thread-2" java.util.ConcurrentModificationException
18:08:04,666 ERROR [stderr] (pool-12-thread-2) 	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1429)
18:08:04,666 ERROR [stderr] (pool-12-thread-2) 	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
18:08:04,666 ERROR [stderr] (pool-12-thread-2) 	at io.undertow.servlet.util.IteratorEnumeration.nextElement(IteratorEnumeration.java:44)
18:08:04,666 ERROR [stderr] (pool-12-thread-2) 	at org.jboss.weld.util.collections.EnumerationList.<init>(EnumerationList.java:42)
18:08:04,666 ERROR [stderr] (pool-12-thread-2) 	at org.jboss.weld.context.beanstore.http.RequestBeanStore.getAttributeNames(RequestBeanStore.java:48)
18:08:04,667 ERROR [stderr] (pool-12-thread-2) 	at org.jboss.weld.context.beanstore.AttributeBeanStore.getPrefixedAttributeNames(AttributeBeanStore.java:198)
18:08:04,667 ERROR [stderr] (pool-12-thread-2) 	at org.jboss.weld.context.beanstore.AttributeBeanStore.attach(AttributeBeanStore.java:95)
18:08:04,667 ERROR [stderr] (pool-12-thread-2) 	at org.jboss.weld.context.http.HttpRequestContextImpl.associate(HttpRequestContextImpl.java:58)
18:08:04,667 ERROR [stderr] (pool-12-thread-2) 	at org.jboss.weld.context.http.HttpRequestContextImpl.associate(HttpRequestContextImpl.java:38)
18:08:04,667 ERROR [stderr] (pool-12-thread-2) 	at org.jboss.weld.servlet.HttpContextLifecycle.requestInitialized(HttpContextLifecycle.java:216)
18:08:04,667 ERROR [stderr] (pool-12-thread-2) 	at org.jboss.weld.servlet.WeldInitialListener.requestInitialized(WeldInitialListener.java:160)
18:08:04,668 ERROR [stderr] (pool-12-thread-2) 	at io.undertow.servlet.core.ApplicationListeners.requestInitialized(ApplicationListeners.java:216)
18:08:04,668 ERROR [stderr] (pool-12-thread-2) 	at io.undertow.servlet.spec.AsyncContextImpl.setupRequestContext(AsyncContextImpl.java:687)
18:08:04,668 ERROR [stderr] (pool-12-thread-2) 	at io.undertow.servlet.spec.AsyncContextImpl.onAsyncComplete(AsyncContextImpl.java:597)
18:08:04,668 ERROR [stderr] (pool-12-thread-2) 	at io.undertow.servlet.spec.AsyncContextImpl.complete(AsyncContextImpl.java:275)
18:08:04,668 ERROR [stderr] (pool-12-thread-2) 	at org.jboss.resteasy.plugins.server.servlet.Servlet3AsyncHttpRequest$Servlet3ExecutionContext$Servle3AsychronousResponse.resume(Servlet3AsyncHttpRequest.java:97)
18:08:04,669 ERROR [stderr] (pool-12-thread-2) 	at async.AsyncHandler$1.onSuccess(AsyncHandler.java:64)
18:08:04,669 ERROR [stderr] (pool-12-thread-2) 	at async.AsyncHandler$1.onSuccess(AsyncHandler.java:61)
18:08:04,669 ERROR [stderr] (pool-12-thread-2) 	at com.google.common.util.concurrent.Futures$4.run(Futures.java:1181)
18:08:04,669 ERROR [stderr] (pool-12-thread-2) 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
18:08:04,669 ERROR [stderr] (pool-12-thread-2) 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
18:08:04,669 ERROR [stderr] (pool-12-thread-2) 	at java.lang.Thread.run(Thread.java:745)
</pre>
