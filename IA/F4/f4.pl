member(X, [X|_]).
member(X, [_|R]):-member(X,R).

retira_comp([Cx|Cn], X, Cx, Cn):-member(X,Cx).
retira_comp([C|Cn], X, Cx, [C|R]):-retira_comp(Cn, X, Cx, R).

triang(N,B):-triang(N,0,B). 
triang(N,N,0).
triang(N,I,B):-
    B > 0, !,
    I > N, !, 
    In is I+B,
    Bn is B-1,
    triang(N, In, Bn).