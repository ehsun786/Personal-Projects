p.fitnessfcn=@ps_example;
p.nvars=2;
p.lb=[-6 -6];
p.ub=[6 6];
p.solver='ga';
p.options=gaoptimset('ga','PopulationSize',20,'CrossoverFraction',0.4,'MaxGeneration',39);
[x,fval]=ga(p); 

% p.fitnessfcn=@ps_example;
% p.nvars=2;
% p.lb=[-6 -6];
% p.ub=[6 6];
% p.solver='ga';
% p.options=optimoptions('ga','PopulationSize',40,'CrossoverFraction',0.8,'MaxGeneration',19);
% [x,fval]=ga(p); 

