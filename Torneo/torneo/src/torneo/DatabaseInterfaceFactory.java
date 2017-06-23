/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import databaseinterface.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;

/**
 *
 * @author nautilus
 */
public class DatabaseInterfaceFactory {
    
    //Registry myReg;
    
    DatabaseInterfaceArbitro ArbitroTable;
    DatabaseInterfaceCartellino CartellinoTable;
    DatabaseInterfaceGiocatore GiocatoreTable;
    DatabaseInterfaceGoal GoalTable;
    DatabaseInterfaceLogin LoginTable;
    DatabaseInterfacePartita PartitaTable;
    DatabaseInterfaceSquadra SquadraTable;
    DatabaseInterfaceTorneo TorneoTable;
    DatabaseInterfaceTorneoEliminazionediretta TorneoEliminazionedirettaTable;
    DatabaseInterfaceTorneoItaliana TorneoItalianaTable;
                
    
    public DatabaseInterfaceFactory(){    
        
        try{
                //myReg = LocateRegistry.getRegistry("127.0.0.1", 1099);
                
//                ArbitroTable = (DatabaseInterfaceArbitro)myReg.lookup("DBArbitro");
//                CartellinoTable = (DatabaseInterfaceCartellino)myReg.lookup("DBCartellino");
//                GiocatoreTable = (DatabaseInterfaceGiocatore)myReg.lookup("DBGiocatore");
//                GoalTable = (DatabaseInterfaceGoal)myReg.lookup("DBGoal");
//                LoginTable = (DatabaseInterfaceLogin)myReg.lookup("DBLogin");
//                PartitaTable = (DatabaseInterfacePartita)myReg.lookup("DBPartita");
//                SquadraTable = (DatabaseInterfaceSquadra)myReg.lookup("DBSquadra");
//                TorneoTable = (DatabaseInterfaceTorneo)myReg.lookup("DBTorneo");
//                TorneoEliminazionedirettaTable = (DatabaseInterfaceTorneoEliminazionediretta)myReg.lookup("DBTorneoEliminazionediretta");
//                TorneoItalianaTable = (DatabaseInterfaceTorneoItaliana)myReg.lookup("DBTorneoItaliana");

                ArbitroTable = (DatabaseInterfaceArbitro)Naming.lookup("rmi://localhost/DBArbitro");
                CartellinoTable = (DatabaseInterfaceCartellino)Naming.lookup("rmi://localhost/DBCartellino");
                GiocatoreTable = (DatabaseInterfaceGiocatore)Naming.lookup("rmi://localhost/DBGiocatore");
                GoalTable = (DatabaseInterfaceGoal)Naming.lookup("rmi://localhost/DBGoal");
                LoginTable = (DatabaseInterfaceLogin)Naming.lookup("rmi://localhost/DBLogin");
                PartitaTable = (DatabaseInterfacePartita)Naming.lookup("rmi://localhost/DBPartita");
                SquadraTable = (DatabaseInterfaceSquadra)Naming.lookup("rmi://localhost/DBSquadra");
                TorneoTable = (DatabaseInterfaceTorneo)Naming.lookup("rmi://localhost/DBTorneo");
                TorneoEliminazionedirettaTable = (DatabaseInterfaceTorneoEliminazionediretta)Naming.lookup("rmi://localhost/DBTorneoEliminazionediretta");
                TorneoItalianaTable = (DatabaseInterfaceTorneoItaliana)Naming.lookup("rmi://localhost/DBTorneoItaliana");
        } catch(Exception e){
            e.getMessage();
        }
    }
    
    public DatabaseInterfaceArbitro makeArbitroTable(){       
        return ArbitroTable;
    }
    
    public DatabaseInterfaceCartellino makeCartellinoTable(){       
        return CartellinoTable;
    }
    
    public DatabaseInterfaceGiocatore makeGiocatoreTable(){       
        return GiocatoreTable;
    }
    
    public DatabaseInterfaceGoal makeGoalTable(){       
        return GoalTable;
    }
    
    public DatabaseInterfaceLogin makeLoginTable(){       
        return LoginTable;
    }
    
    public DatabaseInterfacePartita makePartitaTable(){       
        return PartitaTable;
    }
    
    public DatabaseInterfaceSquadra makeSquadraTable(){       
        return SquadraTable;
    }
    
    public DatabaseInterfaceTorneo makeTorneoTable(){       
        return TorneoTable;
    }
    
    public DatabaseInterfaceTorneoEliminazionediretta makeTorneoEliminazionedirettaTable(){       
        return TorneoEliminazionedirettaTable;
    }
    
    public DatabaseInterfaceTorneoItaliana makeTorneoItalianaTable(){       
        return TorneoItalianaTable;
    }
}
