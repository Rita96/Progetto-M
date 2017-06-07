/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneo;

import databaseinterface.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author nautilus
 */
public class DatabaseInterfaceFactory {
    
    Registry myReg;
    
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
                myReg = LocateRegistry.getRegistry("127.0.0.1", 1099);
                
                ArbitroTable = (DatabaseInterfaceArbitro)myReg.lookup("DBArbitro");
                CartellinoTable = (DatabaseInterfaceCartellino)myReg.lookup("DBCartellino");
                GiocatoreTable = (DatabaseInterfaceGiocatore)myReg.lookup("DBGiocatore");
                GoalTable = (DatabaseInterfaceGoal)myReg.lookup("DBGoal");
                LoginTable = (DatabaseInterfaceLogin)myReg.lookup("DBLogin");
                PartitaTable = (DatabaseInterfacePartita)myReg.lookup("DBPartita");
                SquadraTable = (DatabaseInterfaceSquadra)myReg.lookup("DBSquadra");
                TorneoTable = (DatabaseInterfaceTorneo)myReg.lookup("DBTorneo");
                TorneoEliminazionedirettaTable = (DatabaseInterfaceTorneoEliminazionediretta)myReg.lookup("DBTorneoEliminazionediretta");
                TorneoItalianaTable = (DatabaseInterfaceTorneoItaliana)myReg.lookup("DBTorneoItaliana");
                
                }catch(Exception e){e.getMessage();}
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
