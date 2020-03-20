# COMP30880

Team Pah tail
-Dongryul Jeong
-Donnchadh Robinson
-Michael Bradley

Michael Bradley, ProjectListGenerator.java: 
The ProjectListGenerator class works by first creating a list of StaffMember instances using the info from the rows of Miskatonic Staff Members.csv.
It then proposes from those staffMembers a list of random Project instances of size n, where each project has a unique staffMember/Supervisor.
If in the proposed list contains a set of Supervisors where between all Supervisors it is possible to generate a total of n*3
projects then we add an additional n*2 projects to our list of projects using only previously used supervisors and write it to ProjectList.csv.
If the proposed list doesn't contain a suitable set of Supervisors then we remove 10 staff members who are not capable of generating 3=< projects and
propose a new list (We remove projects to significantly reduce processing time).
