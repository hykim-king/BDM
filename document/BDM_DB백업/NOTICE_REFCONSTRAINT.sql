--------------------------------------------------------
--  Ref Constraints for Table NOTICE
--------------------------------------------------------

  ALTER TABLE "BDM"."NOTICE" ADD CONSTRAINT "FK_USERS_TO_NOTICE" FOREIGN KEY ("ID")
	  REFERENCES "BDM"."USERS" ("ID") ON DELETE CASCADE ENABLE;
