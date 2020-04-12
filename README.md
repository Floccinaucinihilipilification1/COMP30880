# COMP30880

Team Pah tail
-Dongryul Jeong (15205766)
-Donnchadh Robinson (16742991)
-Michael Bradley (16407672)

Task1:

Michael Bradley, ProjectListGenerator.java: 
The ProjectListGenerator class works by first creating a list of StaffMember instances using the info from the rows of Miskatonic Staff Members.csv.
It then proposes from those staffMembers a list of random Project instances of size n, where each project has a unique staffMember/Supervisor.
If in the proposed list contains a set of Supervisors where between all Supervisors it is possible to generate a total of n*3
projects then we add an additional n*2 projects to our list of projects using only previously used supervisors and write it to ProjectList.csv.
If the proposed list doesn't contain a suitable set of Supervisors then we remove 10 staff members who are not capable of generating 3=< projects and
propose a new list (We remove projects to significantly reduce processing time).

Donnchadh Robinson, StudentGenerator.Java:
The student generator creates a lists of the CS, DS projects and the list of student names.
Using the information found in the ProjectList.csv file and the names.csv . The name and
ID of each student is then generated. Then a list of projects is created with it's preference 
percentages decided by the normal distribution. The student is then assigned a random stream
based on the 60/40 split. The student is then assigned 10 projects based on the preferences
previously decided. 




Normal Distribution:
To create the normal distribution the project uses three lists that it assigns projects to.
The lists are shuffled so that they are random for each list of preferences.
The list is then broken into thirds and different percentages are assigned to each.
The first third of the list is 68% likely to be chosen.
The second third is 27% likely to be chosen.
The third list is 5% likey to be chosen.
This will create the standard deviation in the preference lists of the students. 

Task2:
We took in the information from the files and used an
if statement to detect if a csv or tsv file was being read in 
and changed the delimiter accordingly. This information was then
stored in lists for further use. We added code to account for things
like extra white spaces, comma's within the text for csv's and similar 
situations. 

We tested the code using a varity of files with a few different errors.
Including files missing information and files with extra unwanted characters.

Task4:
SimulatedAnnealing class contains three static methods, Fitness(), Energy(), and ChangeRandom().
Fitness calculates the fitness of a CandidateSolution argument based on soft and hard constraints. Given that it is still possible for a two students to be assigned the same project and likely to happen the energy returned is often 0. 
Energy returns the inverse of Fitness (or infinity if fitness is 0).
ChangeRamdom takes a CandidateSolution as an argument and randomly changes one students project to another one from their preference list. 

A basic menu was added to allow easy navigation of the program and to allow the solution to be worked on without generating a new solution each time. 



