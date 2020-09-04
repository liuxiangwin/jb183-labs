#!/bin/bash
echo "Creating userroles Security Domain"
sh /opt/eap/bin/jboss-cli.sh --connect "/subsystem=security/security-domain=userroles/:add(cache-type=default)"
sh /opt/eap/bin/jboss-cli.sh --connect "/subsystem=security/security-domain=userroles/authentication=classic:add(login-modules=[{'code'=>'UsersRoles', 'flag'=>'required', 'module-options'=>['usersProperties'=>'file:///home/student/JB183/labs/securing-lab/todo-users.properties','rolesProperties'=>'file:///home/student/JB183/labs/securing-lab/todo-roles.properties'] }])"
sh /opt/eap/bin/jboss-cli.sh --connect ":reload"
