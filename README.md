# Laboratorio per il corso di Algoritmi e Strutture Dati: informazioni generali

Il presente documento riporta i testi degli esercizi da svolgere e consegnare al fine di poter sostenere la prova di laboratorio per il corso di Algoritmi e Strutture Dati, preceduti da alcune indicazioni e suggerimenti relativi allo svolgimento degli stessi.

**Importante**:

Gli esercizi sono divisi in una parte di sviluppo di una struttura dati e/o di un algoritmo e una (eventuale) parte in cui si esegue il codice sviluppato su un dataset dato. È importante tenere presente che nello sviluppare la prima parte degli esercizi si deve assumere di stare sviluppando una libreria generica intesa come fondamento di futuri programmi. Non è pertanto lecito fare assunzioni semplificative legate alla particolare applicazione della libreria ai dati forniti. Ad esempio non si può assumere che l'input di un algoritmo di ordinamento sia un vettore di un particolare tipo di elementi o che i valori dei nodi di un grafo siano necessariamente di tipo stringa.

In alcuni esercizi si ribadisce la necessità di implementare una versione generale della libreria. Ciò non vuol dire che dove questo non sia specificato esplicitamente sia lecita una implementazione meno generale.

*Durante l'esame sarete chiamati a difendere la generalità dell'implementazione proposta.*

## Unit testing

Come indicato esplicitamente nei testi degli esercizi, il progetto di laboratorio comprende anche la definizione di opportune suite di unit tests.
Si rammenta, però, che il focus del laboratorio è l'implementazione di strutture dati e algoritmi. Relativamente agli unit-test sarà quindi sufficiente che gli studenti dimostrino di averne colto il senso e di saper realizzare una suite di test sufficiente a coprire i casi più comuni (compresi, in particolare, i casi limite).


## Linguaggio in cui sviluppare il laboratorio

È lasciata libertà allo studente di implementare il codice usando Java o C. Come potrete verificare gli esercizi chiedono di realizzare strutture generiche. Seguono alcuni suggerimenti sul modo di realizzarle nei due linguaggi accettati.

**Nota importante**: Con "strutture dati generiche" si fa riferimento al fatto che le strutture dati realizzate devono poter essere utilizzate con tipi di dato non noti a tempo di compilazione. Sebbene in Java la soluzione più  in linea con il moderno utilizzo del linguaggio richiederebbe la creazione di classi parametriche, tutte le scelte implementative  (compresa la decisione di usare o meno classi parametriche) sono lasciate agli studenti.

**Suggerimenti (C)**: Nel caso del C, è necessario capire come meglio approssimare l'idea di strutture generiche utilizzando quanto permesso dal linguaggio. Un approccio comune è far sì che le funzioni che manipolano le strutture dati prendano in input puntatori a void e utilizzino qualche funzione fornita dall'utente per accedere alle componenti necessarie.
*Nota*: chi è in grado di realizzare tipi di dato astratto tramite tipi opachi è incoraggiato a procedere in questa direzione.

**Suggerimenti (Java)**: Nel caso si scelga di utilizzare Java, è possibile (e consigliato) usare gli ArrayList invece degli array nativi al fine di semplificare l'implementazione delle strutture generiche.

## Uso di librerie esterne e/o native del linguaggio scelto

A parte gli ArrayList è vietato usare altre strutture dati *di base* offerte dal linguaggio in uso (es. code, liste, stack, ...). È lecito (ma non obbligatorio) avvalersi di strutture dati più complesse quando la loro realizzazione non è richiesta da uno degli esercizi proposti. Ad esempio è lecito utilizzare una libreria che implementa un dizionario (e.g., HashTable), ma non una che implementa i grafi (poiché questi ultimi sono oggetto di un esercizio).

## Qualità dell'implementazione

È parte del mandato degli esercizi la realizzazione di codice di buona qualità.
Per "buona qualità" intendiamo codice ben modularizzato, ben commentato e
ben testato.

# Esercizio 1

Implementare una libreria che offre i seguenti algoritmi di ordinamento:

- Insertion Sort
- Merge Sort

Ogni algoritmo va implementato in modo tale da poter essere utilizzato su un generico tipo ```T```. L'implementazione degli algoritmi deve permettere di specificare il criterio secondo cui ordinare i dati. *Suggerimento*: Usare l'interfaccia java.util.Comparator (o, nel caso di una implementazione C, un puntatore a funzione).

## Unit testing

Implementare gli unit-test degli algoritmi secondo le indicazioni suggerite nel documento [Unit Testing](UnitTesting.md).

## Uso della libreria di ordinamento implementata

### Ordinamento di un array di interi

Il file `integers.txt` che potete trovare seguendo il path

    /usr/NFS/Linux/labalgoritmi/datasets/

(in laboratorio von Neumann, selezionare il disco Y) contiene 20 milioni di interi da ordinare. Gli interi sono scritti di seguito, ciascuno su una riga. *Gli interi variano su un range molto ampio* (interi a 32 bit potrebbero non essere in grado di rappresentare adeguatamente i valori contenuti nel file).

Implementare un'applicazione che, usando ciascuno degli algoritmi di ordinamento offerti dalla libreria, ordina in modo crescente gli interi contenuti nel file `integers.txt`.


### Implementazione di un test su array di interi

Implementare una funzione che accetta in input un intero N e un qualunque array A di interi e che verifica se A contiene due interi la cui somma è esattamente N. La funzione DEVE avere complessità $\Theta(KlogK)$ sul numero K di elementi dell'array A.

Il file `sums.txt` che potete trovare seguendo il path

    /usr/NFS/Linux/labalgoritmi/datasets/

(in laboratorio von Neumann, selezionare il disco Y) contiene alcuni interi. Gli interi sono scritti di seguito, ciascuno su una riga.

Come esempio di uso della funzione, implementare un'applicazione che carica in un array A gli interi contenuti nel file `integers.txt` e, per ciascun intero N contenuto nel file `sums.txt`, verifica se esso è la somma di due elementi contenuti in A.

**IL FILE `sums.txt` NON DEVE ESSERE OGGETTO DI COMMIT SU GIT!**

# Esercizio 2

Si consideri il problema  di determinare la distanza di edit tra due stringhe (Edit distance): date due stringhe s1 e s2, non necessariamente della stessa lunghezza, determinare il minimo numero di operazioni necessarie per trasformare la stringa s2 in s1. Si assuma che le operazioni disponibili siano soltanto due: cancellazione e inserimento di un carattere. Esempi:

- "casa" e "cassa" hanno edit distance pari a 1 (1 cancellazione);
- "casa" e "cara" hanno edit distance pari a 2 (1 cancellazione + 1 inserimento);
- "tassa" e "passato" hanno edit distance pari a 4 (3 cancellazioni + 1 inserimento);
- "pioppo" e "pioppo" hanno edit distance pari a 0.

Si implementi una versione ricorsiva della funzione `edit_distance` basata sulle seguenti osservazioni (indichiamo con $|s|$ la lunghezza di $s$ e con $\mathrm{rest}(s)$ la sottostringa di $s$ ottenuta ignorando il primo carattere di $s$):

- se $|s1|$ = 0, allora $\mathrm{edit\_distance}(s1,s2) = |s2|$;
- se $|s2|$ = 0, allora $\mathrm{edit\_distance}(s1,s2) = |s1|$;
- altrimenti, siano:
  - $d_{\mathrm{no-op}} = \
      \begin{cases} \
        \mathrm{edit\_distance}(\mathrm{rest}(s1),\mathrm{rest}(s2)) & \mathrm{se\ } s1[0]=s2[0] \\ \
        \infty & \mathrm{altrimenti} \
      \end{cases}$
  - $d_{\mathrm{canc}} = 1+ \mathrm{edit\_distance}(s1,\mathrm{rest}(s2))$
  - $d_{\mathrm{ins}} = 1+ \mathrm{edit\_distance}(\mathrm{rest}(s1),s2)$

  Si ha: $\mathrm{edit\_distance}(s1,s2) = \min\{d_{\mathrm{no-op}},d_{\mathrm{canc}},d_{\mathrm{ins}}\}$

Si implementi una seconda versione `edit_distance_dyn` della funzione, adottando una strategia di programmazione dinamica.

**Nota**: Le definizioni sopra riportate non corrispondono al modo usuale di definire la distanza di edit, né si prestano a una implementazione iterativa particolarmente efficiente. Sono del tutto sufficienti però per risolvere l'esercizio e sono quelle su cui dovrete basare la vostra risposta.


## Unit Testing

Implementare gli unit-test degli algoritmi secondo le indicazioni suggerite nel documento [Unit Testing](UnitTesting.md).

## Uso delle funzioni implementate
Il file `dictionary.txt` che potete trovare seguendo il path

    /usr/NFS/Linux/labalgoritmi/datasets/

(in laboratorio von Neumann, selezionare il disco Y) contiene l'elenco (di una parte significativa) delle parole italiane. Le parole sono scritte di seguito, ciascuna su una riga.

Il file `correctme.txt` contiene una citazione di John Lennon. Il file contiene alcuni errori di battitura.

Si implementi un'applicazione che usa la funzione `edit_distance_dyn` per determinare, per ogni parola `w` in `correctme.txt`, la lista di parole in `dictionary.txt` con edit distance minima da `w`. Si sperimenti il funzionamento dell'applicazione e si riporti in una breve relazione (circa una pagina) i risultati degli esperimenti.

**I FILE `dictionary.txt` E `correctme.txt` NON DEVONO ESSERE OGGETTO DI COMMIT SU GIT!**

# Esercizio 3

Si implementi la struttura dati Coda con priorità.

La struttura dati deve gestire tipi generici e consentire un numero qualunque e non noto a priori di elementi.


## Unit Testing

Implementare gli unit-test degli algoritmi secondo le indicazioni suggerite nel documento [Unit Testing](UnitTesting.md).


# Esercizio 4

Si implementi una libreria che realizza la struttura dati Grafo in modo che sia ottimale per dati sparsi (IMPORTANTE: le scelte implementative che farete dovranno essere giustificate in relazione alle nozioni presentate durante le lezioni in aula). La struttura deve consentire di rappresentare sia grafi diretti che grafi non diretti (suggerimento: un grafo non diretto può essere rappresentato usando un'implementazione per grafi diretti nel seguente modo: per ogni arco `(a,b)`, etichettato `w`, presente nel grafo, è presente nel grafo anche l'arco `(b,a)`, etichettato `w`. Ovviamente, il grafo dovrà mantenere l'informazione che specifica se esso è un grafo diretto o non diretto.).

Oltre alle funzioni essenziali per la struttura dati Grafo, si implementi nella libreria anche una funzione che restituisce il peso del grafo (se il grafo non è pesato, la funzione può terminare con un errore).

## Unit Testing

Implementare gli unit-test degli algoritmi secondo le indicazioni suggerite nel documento [Unit Testing](UnitTesting.md).

## Uso della libreria che implementa la struttura dati Grafo

Si implementi l'algoritmo di Prim per la determinazione della minima foresta  ricoprente di un grafo. L'implementazione dell'algoritmo di Prim dovrà utilizzare la struttura dati Coda con priorità nell'esercizio precedente.

N.B. Nel caso in cui il grafo sia costituito da una sola componente connessa, l'algoritmo restituirà un albero; nel caso in cui, invece, vi siano più componenti connesse, l'algoritmo restituirà una foresta costituita dai minimi alberi ricoprenti di ciascuna componente connessa.

## Uso delle librerie che implementano la struttura dati Grafo e l'algoritmo di Prim

La struttura dati e l'algoritmo di Prim dovranno essere utilizzati con i dati contenuti nel file `italian_dist_graph.csv`.

Il file `italian_dist_graph.csv` che potete recuperare seguendo il path

    /usr/NFS/Linux/labalgoritmi/datasets/

(in laboratorio von Neumann, selezionare il disco Y) contiene le distanze in metri tra varie località italiane e una frazione delle località a loro più vicine. Il formato è un CSV standard: i campi sono separati da virgole; i record sono separati dal carattere di fine riga (`\n`).

Ogni record contiene i seguenti dati:

- località 1: (tipo stringa) nome della località "sorgente". La stringa può   contenere spazi, non può contenere virgole;
- località 2: (tipo stringa) nome della località "destinazione". La stringa  può contenere spazi, non può contenere virgole;
- distanza: (tipo float) distanza in metri tra le due località.

*Note:*

- potete intrepretare le informazioni presenti nelle righe del file come   archi **non diretti** (i.e., probabilmente vorrete inserire nel vostro grafo sia l'arco di andata che quello di ritorno a fronte di ogni riga letta).
- il file è stato creato a partire da un dataset poco accurato. I dati riportati contengono inesattezze e imprecisioni.


## Controlli

Un'implementazione corretta dell'algoritmo di Prim, eseguita sui dati contenuti nel file `italian_dist_graph.csv`, dovrebbe determinare una minima foresta ricoprente con 18.640 nodi, 18.637 archi (non orientati) e di peso complessivo di circa 89.939,913 Km.
