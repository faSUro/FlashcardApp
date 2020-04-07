package it.fasuro.flashcardApp.model;

import java.util.TreeMap;

public class Deck {
	
	private TreeMap<String, String> fcToStudy = new TreeMap<String, String>();
	
	public Deck() {
		fcToStudy.put("Descrivere il cervello", "Il cervello umano è l'organo principale del sistema nervoso umano e, insieme al midollo spinale, costituisce il sistema"
				+ " nervoso centrale. Il cervello è costituito dal telencefalo e da diencefalo. Esso controlla la maggior parte delle attività dell'intero organismo, elaborando, "
				+ "integrando e coordinando le informazioni che riceve dagli organi di senso e prendendo decisioni in merito alle istruzioni da inviare al resto del corpo. Il cervello"
				+ " è contenuto e protetto dalle scatola cranica. Il telencefalo è la parte più grande del cervello umano ed è diviso in due emisferi cerebrali. La corteccia cerebrale è "
				+ "uno strato esterno di materia grigia, che copre il nucleo della materia bianca. La corteccia è divisa in neocorteccia e l'allocorteccia che è molto più piccola. La "
				+ "neocorteccia è composta da sei strati neuronali, mentre l'allocorteccia ne ha tre o quattro. Ogni emisfero è convenzionalmente suddiviso in quattro lobi: il lobo "
				+ "frontale, il lobo parietale, il lobo occipitale e il lobo temporale. Il lobo frontale è deputato alle funzioni esecutive, quali l'autocontrollo, la pianificazione, "
				+ "il ragionamento e il pensiero astratto, mentre il lobo occipitale è dedicato alla vista. All'interno di ciascun lobo, le aree corticali sono associate a funzioni "
				+ "specifiche, come le regioni sensoriali, motorie e associative. Sebbene gli emisferi sinistro e destro siano sostanzialmente simili per forma e funzione, alcune "
				+ "funzioni sono associate a un lato, come il linguaggio nella parte sinistra e le capacità visuali-spaziali nella destra. Gli emisferi sono collegati da tratti nervosi "
				+ "commissurali, il più grande dei quali è il corpo calloso. Il cervello è collegato al midollo spinale grazie al tronco cerebrale. Quest'ultimo è formato dal mesencefalo,"
				+ " dal ponte di Varolio e dal midollo allungato. Il cervelletto è collegato, a sua volta, al tronco cerebrale da coppie di peduncoli. All'interno del cervello vi è il "
				+ "sistema ventricolare, costituito da quattro ventricoli interconnessi in cui viene prodotto e fatto circolare il liquido cerebrospinale. Sotto la corteccia cerebrale "
				+ "sono presenti diverse strutture importanti, tra cui il talamo, l'epitalamo, la ghiandola pineale, l'ipotalamo, l'ipofisi e il subtalamo; le strutture limbiche, tra "
				+ "cui l'amigdala e l'ippocampo; il claustro, i vari nuclei dei gangli della base; le strutture del proencefalo basale e i tre organi circumventricolari. Le cellule del "
				+ "cervello comprendono i neuroni e le cellule gliali di supporto. Vi sono oltre 86 miliardi di neuroni nel cervello e un numero più o meno uguale di altre cellule. "
				+ "L'attività cerebrale è resa possibile dalle interconnessioni tra i neuroni e dal loro rilascio di neurotrasmettitori in risposta agli impulsi nervosi. I neuroni si "
				+ "connettono per formare percorsi neurali e complesse reti neurali. Il cervello è protetto dal cranio, sospeso nel liquido cerebrospinale e isolato dal flusso sanguigno "
				+ "dalla barriera emato-encefalica. Nonostante tutto ciò, è comunque suscettibile di lesioni, malattie e infezioni. Le lesioni possono essere causate da un trauma fisico "
				+ "o una perdita di afflusso di sangue, una condizione nota come ictus. Il cervello è suscettibile di disturbi degenerativi, come la malattia di Parkinson, le demenze (tra "
				+ "cui la malattia di Alzheimer) e la sclerosi multipla. Si ritiene che le condizioni psichiatriche, incluse la schizofrenia e la depressione clinica, siano associate a "
				+ "disfunzioni cerebrali. Il cervello può anche essere sito di tumori, sia benigni che maligni; quest'ultimi principalmente provengono da altri siti nel corpo (metastasi)."
				+ " Lo studio dell'anatomia del cervello prende il nome di neuroanatomia, mentre lo studio della sua funzione è la neuroscienza. Un certo numero di tecniche sono "
				+ "utilizzate per studiare il cervello. Campioni prelevati da cadaveri o da altri animali, esaminati al microscopio, hanno tradizionalmente fornito molte informazioni "
				+ "per comprenderne struttura e funzionamento. Le tecnologie di imaging medicale come il neuroimaging funzionale e le registrazioni elettroencefalografiche (EEG) sono"
				+ " importanti per lo studio del cervello. L'anamnesi delle persone con lesioni cerebrali ha fornito informazioni sulla funzione di ciascuna parte del cervello.");
		fcToStudy.put("Chi ha inventato il computer?", "Il primo computer elettronico e programmabile si chiamava Colossus. Fu inventato dagli inglesi durante la Seconda guerra Mondiale per decifrare i messaggi segreti dei nemici tedeschi. Il suo aiuto fu indispensabile per far vincere la guerra agli Alleati.\n" + 
				"\n" + 
				"Infatti quella macchina ogni giorno era in grado di decifrare 4 mila messaggi segreti degli eserciti nemici.");
		fcToStudy.put("Question 3", "Answer 3");
		fcToStudy.put("Question 4", "Answer 4");
		fcToStudy.put("Question 5", "Answer 5");
		
	}

	public TreeMap<String, String> getFcToStudy() {
		return fcToStudy;
	}

}
