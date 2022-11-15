package md.dolinschi.screw.reflection;

import md.dolinschi.screw.mapper.DeepMapper;
import md.dolinschi.screw.reflection.dummy.DummyReference;
import md.dolinschi.screw.reflection.dummy.DummyTarget;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeepMapperTest {
    @Test
    void testDeepMapping() {
        LocalTime localTime = LocalTime.now();
        final var dummyReference = new DummyReference(0, true, "reference",
                new DummyReference(1, false, "innerReference", null));
        final var dummyTarget = new DummyTarget();
        DeepMapper.mapOf(dummyReference, dummyTarget);
        LocalTime localTime1 = LocalTime.now();
        System.out.println(localTime.getNano());
        System.out.println(localTime1.getNano());

        assertEquals(dummyReference.getInteger(), dummyTarget.getInteger());
        assertEquals(dummyReference.getString(), dummyTarget.getString());
        assertEquals(dummyReference.isaBoolean(), dummyTarget.isaBoolean());
        assertEquals(dummyTarget.getDummyReference(), new DummyTarget(1, false, "innerReference", null));
        assertEquals(new HashSet<>(dummyReference.getToSet()), dummyTarget.getToSet());
    }
}