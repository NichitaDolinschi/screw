package md.dolinschi.screwy.reflection;

import md.dolinschi.screwy.mapper.DeepMapper;
import md.dolinschi.screwy.reflection.dummy.DummyReference;
import md.dolinschi.screwy.reflection.dummy.DummyTarget;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeepMapperTest {
    @Test
    void testDeepMapping() {
        final var dummyReference = new DummyReference(0, true, "reference",
                new DummyReference(1, false, "innerReference", null));
        final var dummyTarget = new DummyTarget();
        DeepMapper.mapOf(dummyReference, dummyTarget);
        assertEquals(dummyReference.getInteger(), dummyTarget.getInteger());
        assertEquals(dummyReference.getString(), dummyTarget.getString());
        assertEquals(dummyReference.isaBoolean(), dummyTarget.isaBoolean());
        assertEquals(dummyTarget.getDummyReference(), new DummyTarget(1, false, "innerReference", null));
    }
}