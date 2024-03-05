--------------------------------------------------------
--  Ref Constraints for Table BULLETIN
--------------------------------------------------------

  ALTER TABLE "BDM"."BULLETIN" ADD CONSTRAINT "FK_USERS_TO_BULLETIN" FOREIGN KEY ("ID")
	  REFERENCES "BDM"."USERS" ("ID") ON DELETE CASCADE ENABLE;
