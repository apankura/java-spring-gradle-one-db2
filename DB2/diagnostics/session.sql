-- isolation NLEVELS
-- https://www.ibm.com/support/knowledgecenter/en/SSEPGG_11.1.0/com.ibm.db2.luw.admin.cmd.doc/doc/r0001946.html
-- https://www.ibm.com/developerworks/data/library/techarticle/dm-1107db2isolationlevel/
-- SELECT isolation FROM syscat.packages

db2 terminate
db2 change isolation to ur
db2 connect to sample
