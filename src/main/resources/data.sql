INSERT INTO `role` SELECT * FROM (
    SELECT 2, 'ADMIN'
    ) WHERE NOT EXISTS(SELECT * FROM `role`);

INSERT INTO `role` SELECT * FROM (
    SELECT 4, 'USER'
    ) WHERE NOT EXISTS(SELECT * FROM `role`);

INSERT INTO `user` SELECT * FROM (
    SELECT '00000000000000000000000000000001', 1, 'admin', '$2a$16$rh7NhM2wehvhk1iZlauwZ.4f.FguBjuFQLYYkV437vCeCeimLNNqy'
    ) WHERE NOT EXISTS(SELECT * FROM `user`);

INSERT INTO `user_role` SELECT * FROM (
    SELECT '00000000000000000000000000000001', '2'
    ) WHERE NOT EXISTS(SELECT * FROM `user_role`);