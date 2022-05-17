package org.roegerle.vlingoissue96;

import io.vlingo.xoom.actors.Definition;
import io.vlingo.xoom.actors.testkit.TestActor;
import io.vlingo.xoom.actors.testkit.TestWorld;
import org.junit.jupiter.api.Test;

import java.util.stream.LongStream;

public class Tests {

    @Test
    void replicateError() {
        TestWorld world = TestWorld.startWithDefaults("PayloadStoreTests");

        TestActor<TestProtocol> actor =
                world.actorFor(
                        TestProtocol.class,
                        Definition.has(TestProtocolActor.class, Definition.NoParameters));

        long size = 100000L;
        LongStream.range(0, size)
                .parallel()
                .forEach(
                        value -> {
                            actor.actor().put(value);
                        });

        world.terminate();
    }
}
