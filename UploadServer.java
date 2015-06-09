

import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.VoidHandler;
import org.vertx.java.core.file.AsyncFile;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.streams.Pump;
import org.vertx.java.platform.Verticle;
import java.util.UUID;
public class UploadServer extends Verticle {
public void start() {
vertx.createHttpServer().requestHandler(new Handler<HttpServerRequest>() {
public void handle(final HttpServerRequest req) {
// We first pause the request so we don't receive any data between now and when the file is opened
req.pause();
final String filename = "upload/file-" + UUID.randomUUID().toString() + ".upload";
vertx.fileSystem().open(filename, new AsyncResultHandler<AsyncFile>() {
public void handle(AsyncResult<AsyncFile> ar) {
if (ar.failed()) {
ar.cause().printStackTrace();
return;
}
final AsyncFile file = ar.result();
final Pump pump = Pump.createPump(req, file);
final long start = System.currentTimeMillis();
req.endHandler(new VoidHandler() {
public void handle() {
file.close(new AsyncResultHandler<Void>() {
public void handle(AsyncResult<Void> ar) {
if (ar.succeeded()) {
req.response().end();
long end = System.currentTimeMillis();
System.out.println("Uploaded " + pump.bytesPumped() + " bytes to " + filename + " in " + (end - start) + " ms");
} else {
ar.cause().printStackTrace(System.err);
}
}
});
}
});
pump.start();
req.resume();
}
});
}
}).listen(8080);
}
}
