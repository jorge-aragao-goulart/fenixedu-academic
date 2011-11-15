/*
 * Created on Jan 11, 2005
 */

package net.sourceforge.fenixedu.applicationTier.Servico.projectsManagement;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.Role;
import net.sourceforge.fenixedu.domain.person.RoleType;
import net.sourceforge.fenixedu.domain.projectsManagement.ProjectAccess;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;
import net.sourceforge.fenixedu.persistenceTierOracle.BackendInstance;
import net.sourceforge.fenixedu.persistenceTierOracle.Oracle.PersistentProject;
import net.sourceforge.fenixedu.persistenceTierOracle.Oracle.PersistentProjectUser;

/**
 * @author Susana Fernandes
 */
public class ReviewProjectAccess extends FenixService {

    public void run(Person person, String costCenter, BackendInstance instance, String userNumber) throws FenixServiceException,
	    ExcepcaoPersistencia {

	Role role = Role.getRoleByRoleType(RoleType.PROJECTS_MANAGER);
	if (instance == BackendInstance.IT) {
	    role = Role.getRoleByRoleType(RoleType.IT_PROJECTS_MANAGER);
	}
	if (instance == BackendInstance.IST_ID) {
	    role = Role.getRoleByRoleType(RoleType.ISTID_PROJECTS_MANAGER);
	}
	if (ProjectAccess.getAllByPersonAndCostCenter(person, false, true, instance).size() == 0) {
	    Integer personNumber = getPersonNumber(person);
	    if (personNumber == null) {
		throw new FenixServiceException();
	    }
	    if ((new PersistentProject().countUserProject(personNumber, instance) == 0)) {
		cleanProjectsAccess(person, role);
	    }
	}
	role = Role.getRoleByRoleType(RoleType.INSTITUCIONAL_PROJECTS_MANAGER);
	if (ProjectAccess.getAllByPersonAndCostCenter(person, true, true, BackendInstance.IST).size() == 0) {
	    Integer personNumber = getPersonNumber(person);
	    if (personNumber == null) {
		throw new FenixServiceException();
	    }
	    if ((new PersistentProjectUser().getInstitucionalProjectCoordId(personNumber, BackendInstance.IST).size() == 0)) {
		cleanProjectsAccess(person, role);
	    }
	}
    }

    private Integer getPersonNumber(Person person) {
	if (person.getEmployee() != null) {
	    return person.getEmployee().getEmployeeNumber();
	}
	if (person.getGrantOwner() != null) {
	    return person.getGrantOwner().getNumber();
	}
	return null;
    }

    private void cleanProjectsAccess(Person person, Role role) throws FenixServiceException {
	person.removePersonRoles(role);
    }
}