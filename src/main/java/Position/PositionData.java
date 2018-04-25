package Position;

import org.json.JSONObject;
import java.time.LocalDate;

public class PositionData {
                public Position CreatePosition(String name){
                    Position position = new Position();
                    position.setName(name);
                    position.setShortName("TestShortName");
                    position.setComment("TestComment");
                    position.setScheduleType("Salary");
                    position.setSalary(700);
                    position.setPrepaidExpense(800);
                    return position;
                                                }


                public Position UpdatePosition(String name, int id){
                    Position position = CreatePosition(name);
                    position.setId(id);
                    position.setName(name);
                    position.setShortName("OtherTestShortname");
                    position.setComment("OtherTestComment");
                    position.setScheduleType("Free");
                    position.setSalary(400);
                    position.setPrepaidExpense(900);
                    return position;

                }






}
