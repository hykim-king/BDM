--------------------------------------------------------
--  Ref Constraints for Table HEART
--------------------------------------------------------

  ALTER TABLE "BDM"."HEART" ADD CONSTRAINT "FK_BULLETIN_TO_HEART" FOREIGN KEY ("POST_NO")
	  REFERENCES "BDM"."BULLETIN" ("POST_NO") ON DELETE CASCADE ENABLE;
  ALTER TABLE "BDM"."HEART" ADD CONSTRAINT "FK_USERS_TO_HEART" FOREIGN KEY ("ID")
	  REFERENCES "BDM"."USERS" ("ID") ON DELETE CASCADE ENABLE;
