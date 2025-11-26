alter table medicos add status boolean not null;
UPDATE medicos SET status = true;