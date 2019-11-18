package patterns.observateur;

import java.util.concurrent.Flow;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 * @author Yann Laurillau <yann.laurillau@iut2.univ-grenoble-alpes.fr>
 */
public interface Observateur<Message> extends Flow.Subscriber<Message> {

    @Override
    public default void onSubscribe(Flow.Subscription subscription) {
        /* Do not bound the number of unfulfilled demand for the subscription:
         * we process all incoming messages.*/
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public default void onNext(Message msg) {
        traiterMessage(msg);
    }

    @Override
    public default void onError(Throwable throwable) {
    }

    @Override
    public default void onComplete() {
    }

    public abstract void traiterMessage(Message msg);
}