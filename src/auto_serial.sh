javac -cp /home/laboratory/hadoop-2.7.1/share/hadoop/common/hadoop-common-2.7.1.jar:/home/laboratory/hadoop-2.7.1/share/hadoop/mapreduce/hadoop-mapreduce-client-core-2.7.1.jar:/home/laboratory/hadoop-2.7.1/share/hadoop/mapreduce/hadoop-mapreduce-client-common-2.7.1.jar:/home/laboratory/workspace/moead_parallel/commons-math-2.2-sources.jar:./ mop/CMoChromosome.java -d .

javac -cp ./:/home/laboratory/workspace/chea_parallel/commons-math-2.2.jar:/home/laboratory/workspace/chea_parallel/apache-commons-lang.jar:/home/laboratory/workspace/chea_parallel/*.jar:../ chea/chea.java -d .
jar -cvf chea.jar .
java -cp chea.jar:./:/home/laboratory/workspace/chea_parallel/commons-math-2.2.jar:/home/laboratory/workspace/chea_parallel/apache-commons-lang.jar:/home/laboratory/workspace/chea_parallel/*.jar:../ chea.chea
