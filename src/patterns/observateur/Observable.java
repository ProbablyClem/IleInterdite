package patterns.observateur;

import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 *
 * @author Raphaël Bleuse <raphael.bleuse@iut2.univ-grenoble-alpes.fr>
 * @author Yann Laurillau <yann.laurillau@iut2.univ-grenoble-alpes.fr>
 */
public class Observable<Message> {
    private final SubmissionPublisher<Message> broker
            = new SubmissionPublisher<>(
                    Executors.newSingleThreadExecutor(),
                    Flow.defaultBufferSize()
            );

    public void addObservateur(Observateur<Message> observateur) {
        this.broker.subscribe(observateur);
    }

    public void notifierObservateurs(Message msg) {
        this.broker.submit(msg);
    }
}
