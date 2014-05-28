package net.sourceforge.fenixedu.presentationTier.renderers.providers.executionCourse;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import net.sourceforge.fenixedu.domain.space.SpaceUtils;

import org.fenixedu.spaces.domain.Space;

import pt.ist.fenixWebFramework.rendererExtensions.converters.DomainObjectKeyConverter;
import pt.ist.fenixWebFramework.renderers.DataProvider;
import pt.ist.fenixWebFramework.renderers.components.converters.Converter;

public class RoomsForEducationProvider implements DataProvider {

    @Override
    public Object provide(Object source, Object currentValue) {
        Set<Space> rooms = new TreeSet<Space>(SpaceUtils.ROOM_COMPARATOR_BY_NAME);
        rooms.addAll(SpaceUtils.allocatableSpacesForEducation().collect(Collectors.toList()));
        return rooms;
    }

    @Override
    public Converter getConverter() {
        return new DomainObjectKeyConverter();
    }

}
