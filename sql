select login, password from user
INNER JOIN user_role ON user.id_user = user_role.id_user
LEFT JOIN role ON user_role.id_role = role.id_role
WHERE type_role = 'STUDENT';