%------------------------------------------------------------------------------
% File     : ALG015^7 : TPTP v7.0.0. Released v5.5.0.
% Domain   : General Algebra
% Problem  : Groups 4: CPROPS-ISO-COMPLETE-PROBLEM-1
% Version  : [Ben12] axioms.
% English  :

% Refs     : [Goe69] Goedel (1969), An Interpretation of the Intuitionistic
%          : [CM+04] Colton et al. (2004), Automatic Generation of Classifi
%          : [Ben12] Benzmueller (2012), Email to Geoff Sutcliffe
% Source   : [Ben12]
% Names    : s4-cumul-GAL015+1 [Ben12]

% Status   : Theorem
% Rating   : 1.00 v5.5.0
% Syntax   : Number of formulae    :  183 (   0 unit;  62 type;  32 defn)
%            Number of atoms       : 9631 (  36 equality; 279 variable)
%            Maximal formula depth :   73 (   9 average)
%            Number of connectives : 9443 (   5   ~;   5   |;   9   &;9414   @)
%                                         (   0 <=>;  10  =>;   0  <=;   0 <~>)
%                                         (   0  ~|;   0  ~&)
%            Number of type conns  :  198 ( 198   >;   0   *;   0   +;   0  <<)
%            Number of symbols     :   66 (  62   :;   0   =)
%            Number of variables   :  180 (   2 sgn;  78   !;   7   ?;  95   ^)
%                                         ( 180   :;   0  !>;   0  ?*)
%                                         (   0  @-;   0  @+)
% SPC      : TH0_THM_EQU_NAR

% Comments : Goedel translation of ALG015+1
%------------------------------------------------------------------------------
%----Include axioms for Modal logic S4 under cumulative domains
include('Axioms/LCL015^0.ax').
include('Axioms/LCL013^5.ax').
include('Axioms/LCL015^1.ax').
%------------------------------------------------------------------------------
thf(unit1_type,type,(
    unit1: mu )).

thf(existence_of_unit1_ax,axiom,(
    ! [V: $i] :
      ( exists_in_world @ unit1 @ V ) )).

thf(inv1_type,type,(
    inv1: mu > mu )).

thf(existence_of_inv1_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( inv1 @ V1 ) @ V ) )).

thf(unit2_type,type,(
    unit2: mu )).

thf(existence_of_unit2_ax,axiom,(
    ! [V: $i] :
      ( exists_in_world @ unit2 @ V ) )).

thf(inv2_type,type,(
    inv2: mu > mu )).

thf(existence_of_inv2_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( inv2 @ V1 ) @ V ) )).

thf(h12_type,type,(
    h12: mu > mu )).

thf(existence_of_h12_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h12 @ V1 ) @ V ) )).

thf(h11_type,type,(
    h11: mu > mu )).

thf(existence_of_h11_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h11 @ V1 ) @ V ) )).

thf(h10_type,type,(
    h10: mu > mu )).

thf(existence_of_h10_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h10 @ V1 ) @ V ) )).

thf(h9_type,type,(
    h9: mu > mu )).

thf(existence_of_h9_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h9 @ V1 ) @ V ) )).

thf(h8_type,type,(
    h8: mu > mu )).

thf(existence_of_h8_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h8 @ V1 ) @ V ) )).

thf(h7_type,type,(
    h7: mu > mu )).

thf(existence_of_h7_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h7 @ V1 ) @ V ) )).

thf(h6_type,type,(
    h6: mu > mu )).

thf(existence_of_h6_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h6 @ V1 ) @ V ) )).

thf(h5_type,type,(
    h5: mu > mu )).

thf(existence_of_h5_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h5 @ V1 ) @ V ) )).

thf(h4_type,type,(
    h4: mu > mu )).

thf(existence_of_h4_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h4 @ V1 ) @ V ) )).

thf(h3_type,type,(
    h3: mu > mu )).

thf(existence_of_h3_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h3 @ V1 ) @ V ) )).

thf(h2_type,type,(
    h2: mu > mu )).

thf(existence_of_h2_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h2 @ V1 ) @ V ) )).

thf(e23_type,type,(
    e23: mu )).

thf(existence_of_e23_ax,axiom,(
    ! [V: $i] :
      ( exists_in_world @ e23 @ V ) )).

thf(e22_type,type,(
    e22: mu )).

thf(existence_of_e22_ax,axiom,(
    ! [V: $i] :
      ( exists_in_world @ e22 @ V ) )).

thf(e21_type,type,(
    e21: mu )).

thf(existence_of_e21_ax,axiom,(
    ! [V: $i] :
      ( exists_in_world @ e21 @ V ) )).

thf(e20_type,type,(
    e20: mu )).

thf(existence_of_e20_ax,axiom,(
    ! [V: $i] :
      ( exists_in_world @ e20 @ V ) )).

thf(e13_type,type,(
    e13: mu )).

thf(existence_of_e13_ax,axiom,(
    ! [V: $i] :
      ( exists_in_world @ e13 @ V ) )).

thf(e12_type,type,(
    e12: mu )).

thf(existence_of_e12_ax,axiom,(
    ! [V: $i] :
      ( exists_in_world @ e12 @ V ) )).

thf(e11_type,type,(
    e11: mu )).

thf(existence_of_e11_ax,axiom,(
    ! [V: $i] :
      ( exists_in_world @ e11 @ V ) )).

thf(op2_type,type,(
    op2: mu > mu > mu )).

thf(existence_of_op2_ax,axiom,(
    ! [V: $i,V2: mu,V1: mu] :
      ( exists_in_world @ ( op2 @ V2 @ V1 ) @ V ) )).

thf(e10_type,type,(
    e10: mu )).

thf(existence_of_e10_ax,axiom,(
    ! [V: $i] :
      ( exists_in_world @ e10 @ V ) )).

thf(op1_type,type,(
    op1: mu > mu > mu )).

thf(existence_of_op1_ax,axiom,(
    ! [V: $i,V2: mu,V1: mu] :
      ( exists_in_world @ ( op1 @ V2 @ V1 ) @ V ) )).

thf(h1_type,type,(
    h1: mu > mu )).

thf(existence_of_h1_ax,axiom,(
    ! [V: $i,V1: mu] :
      ( exists_in_world @ ( h1 @ V1 ) @ V ) )).

thf(reflexivity,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [X: mu] :
            ( mbox_s4 @ ( qmltpeq @ X @ X ) ) ) ) )).

thf(symmetry,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [X: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [Y: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ X @ Y ) ) @ ( mbox_s4 @ ( qmltpeq @ Y @ X ) ) ) ) ) ) ) ) )).

thf(transitivity,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [X: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [Y: mu] :
                  ( mbox_s4
                  @ ( mforall_ind
                    @ ^ [Z: mu] :
                        ( mbox_s4 @ ( mimplies @ ( mand @ ( mbox_s4 @ ( qmltpeq @ X @ Y ) ) @ ( mbox_s4 @ ( qmltpeq @ Y @ Z ) ) ) @ ( mbox_s4 @ ( qmltpeq @ X @ Z ) ) ) ) ) ) ) ) ) ) )).

thf(h1_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ A ) @ ( h1 @ B ) ) ) ) ) ) ) ) ) )).

thf(h10_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ A ) @ ( h10 @ B ) ) ) ) ) ) ) ) ) )).

thf(h11_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ A ) @ ( h11 @ B ) ) ) ) ) ) ) ) ) )).

thf(h12_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ A ) @ ( h12 @ B ) ) ) ) ) ) ) ) ) )).

thf(h2_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ A ) @ ( h2 @ B ) ) ) ) ) ) ) ) ) )).

thf(h3_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ A ) @ ( h3 @ B ) ) ) ) ) ) ) ) ) )).

thf(h4_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ A ) @ ( h4 @ B ) ) ) ) ) ) ) ) ) )).

thf(h5_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ A ) @ ( h5 @ B ) ) ) ) ) ) ) ) ) )).

thf(h6_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ A ) @ ( h6 @ B ) ) ) ) ) ) ) ) ) )).

thf(h7_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ A ) @ ( h7 @ B ) ) ) ) ) ) ) ) ) )).

thf(h8_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ A ) @ ( h8 @ B ) ) ) ) ) ) ) ) ) )).

thf(h9_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ A ) @ ( h9 @ B ) ) ) ) ) ) ) ) ) )).

thf(inv1_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ A ) @ ( inv1 @ B ) ) ) ) ) ) ) ) ) )).

thf(inv2_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ A ) @ ( inv2 @ B ) ) ) ) ) ) ) ) ) )).

thf(op1_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4
                  @ ( mforall_ind
                    @ ^ [C: mu] :
                        ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ A @ C ) @ ( op1 @ B @ C ) ) ) ) ) ) ) ) ) ) ) )).

thf(op1_substitution_2,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4
                  @ ( mforall_ind
                    @ ^ [C: mu] :
                        ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ C @ A ) @ ( op1 @ C @ B ) ) ) ) ) ) ) ) ) ) ) )).

thf(op2_substitution_1,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4
                  @ ( mforall_ind
                    @ ^ [C: mu] :
                        ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ A @ C ) @ ( op2 @ B @ C ) ) ) ) ) ) ) ) ) ) ) )).

thf(op2_substitution_2,axiom,
    ( mvalid
    @ ( mbox_s4
      @ ( mforall_ind
        @ ^ [A: mu] :
            ( mbox_s4
            @ ( mforall_ind
              @ ^ [B: mu] :
                  ( mbox_s4
                  @ ( mforall_ind
                    @ ^ [C: mu] :
                        ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ A @ B ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ C @ A ) @ ( op2 @ C @ B ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax1,axiom,
    ( mvalid @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e11 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e11 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e11 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e11 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e12 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e12 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e12 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e12 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e13 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e13 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e13 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e13 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e10 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e10 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e10 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e10 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e12 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e12 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e12 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e12 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e13 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e13 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e13 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e13 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e10 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e10 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e10 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e10 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e11 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e11 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e11 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e11 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e12 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e12 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e12 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e12 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e13 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e13 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e13 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e13 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e10 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e10 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e10 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e10 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e11 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e11 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e11 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e11 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e12 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e12 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e12 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e12 ) @ e13 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e13 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e13 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e13 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e13 ) @ e13 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax2,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e10 ) @ e10 ) @ ( op1 @ e10 @ ( op1 @ e10 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e10 ) @ e11 ) @ ( op1 @ e10 @ ( op1 @ e10 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e10 ) @ e12 ) @ ( op1 @ e10 @ ( op1 @ e10 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e10 ) @ e13 ) @ ( op1 @ e10 @ ( op1 @ e10 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e11 ) @ e10 ) @ ( op1 @ e10 @ ( op1 @ e11 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e11 ) @ e11 ) @ ( op1 @ e10 @ ( op1 @ e11 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e11 ) @ e12 ) @ ( op1 @ e10 @ ( op1 @ e11 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e11 ) @ e13 ) @ ( op1 @ e10 @ ( op1 @ e11 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e12 ) @ e10 ) @ ( op1 @ e10 @ ( op1 @ e12 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e12 ) @ e11 ) @ ( op1 @ e10 @ ( op1 @ e12 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e12 ) @ e12 ) @ ( op1 @ e10 @ ( op1 @ e12 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e12 ) @ e13 ) @ ( op1 @ e10 @ ( op1 @ e12 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e13 ) @ e10 ) @ ( op1 @ e10 @ ( op1 @ e13 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e13 ) @ e11 ) @ ( op1 @ e10 @ ( op1 @ e13 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e13 ) @ e12 ) @ ( op1 @ e10 @ ( op1 @ e13 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e10 @ e13 ) @ e13 ) @ ( op1 @ e10 @ ( op1 @ e13 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e10 ) @ e10 ) @ ( op1 @ e11 @ ( op1 @ e10 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e10 ) @ e11 ) @ ( op1 @ e11 @ ( op1 @ e10 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e10 ) @ e12 ) @ ( op1 @ e11 @ ( op1 @ e10 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e10 ) @ e13 ) @ ( op1 @ e11 @ ( op1 @ e10 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e11 ) @ e10 ) @ ( op1 @ e11 @ ( op1 @ e11 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e11 ) @ e11 ) @ ( op1 @ e11 @ ( op1 @ e11 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e11 ) @ e12 ) @ ( op1 @ e11 @ ( op1 @ e11 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e11 ) @ e13 ) @ ( op1 @ e11 @ ( op1 @ e11 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e12 ) @ e10 ) @ ( op1 @ e11 @ ( op1 @ e12 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e12 ) @ e11 ) @ ( op1 @ e11 @ ( op1 @ e12 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e12 ) @ e12 ) @ ( op1 @ e11 @ ( op1 @ e12 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e12 ) @ e13 ) @ ( op1 @ e11 @ ( op1 @ e12 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e13 ) @ e10 ) @ ( op1 @ e11 @ ( op1 @ e13 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e13 ) @ e11 ) @ ( op1 @ e11 @ ( op1 @ e13 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e13 ) @ e12 ) @ ( op1 @ e11 @ ( op1 @ e13 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e11 @ e13 ) @ e13 ) @ ( op1 @ e11 @ ( op1 @ e13 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e10 ) @ e10 ) @ ( op1 @ e12 @ ( op1 @ e10 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e10 ) @ e11 ) @ ( op1 @ e12 @ ( op1 @ e10 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e10 ) @ e12 ) @ ( op1 @ e12 @ ( op1 @ e10 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e10 ) @ e13 ) @ ( op1 @ e12 @ ( op1 @ e10 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e11 ) @ e10 ) @ ( op1 @ e12 @ ( op1 @ e11 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e11 ) @ e11 ) @ ( op1 @ e12 @ ( op1 @ e11 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e11 ) @ e12 ) @ ( op1 @ e12 @ ( op1 @ e11 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e11 ) @ e13 ) @ ( op1 @ e12 @ ( op1 @ e11 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e12 ) @ e10 ) @ ( op1 @ e12 @ ( op1 @ e12 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e12 ) @ e11 ) @ ( op1 @ e12 @ ( op1 @ e12 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e12 ) @ e12 ) @ ( op1 @ e12 @ ( op1 @ e12 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e12 ) @ e13 ) @ ( op1 @ e12 @ ( op1 @ e12 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e13 ) @ e10 ) @ ( op1 @ e12 @ ( op1 @ e13 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e13 ) @ e11 ) @ ( op1 @ e12 @ ( op1 @ e13 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e13 ) @ e12 ) @ ( op1 @ e12 @ ( op1 @ e13 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e12 @ e13 ) @ e13 ) @ ( op1 @ e12 @ ( op1 @ e13 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e10 ) @ e10 ) @ ( op1 @ e13 @ ( op1 @ e10 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e10 ) @ e11 ) @ ( op1 @ e13 @ ( op1 @ e10 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e10 ) @ e12 ) @ ( op1 @ e13 @ ( op1 @ e10 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e10 ) @ e13 ) @ ( op1 @ e13 @ ( op1 @ e10 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e11 ) @ e10 ) @ ( op1 @ e13 @ ( op1 @ e11 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e11 ) @ e11 ) @ ( op1 @ e13 @ ( op1 @ e11 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e11 ) @ e12 ) @ ( op1 @ e13 @ ( op1 @ e11 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e11 ) @ e13 ) @ ( op1 @ e13 @ ( op1 @ e11 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e12 ) @ e10 ) @ ( op1 @ e13 @ ( op1 @ e12 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e12 ) @ e11 ) @ ( op1 @ e13 @ ( op1 @ e12 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e12 ) @ e12 ) @ ( op1 @ e13 @ ( op1 @ e12 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e12 ) @ e13 ) @ ( op1 @ e13 @ ( op1 @ e12 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e13 ) @ e10 ) @ ( op1 @ e13 @ ( op1 @ e13 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e13 ) @ e11 ) @ ( op1 @ e13 @ ( op1 @ e13 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e13 ) @ e12 ) @ ( op1 @ e13 @ ( op1 @ e13 @ e12 ) ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( op1 @ e13 @ e13 ) @ e13 ) @ ( op1 @ e13 @ ( op1 @ e13 @ e13 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax3,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ unit1 @ e10 ) @ e10 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ unit1 ) @ e10 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ unit1 @ e11 ) @ e11 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ unit1 ) @ e11 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ unit1 @ e12 ) @ e12 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ unit1 ) @ e12 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ unit1 @ e13 ) @ e13 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ unit1 ) @ e13 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ unit1 @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ unit1 @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ unit1 @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ unit1 @ e13 ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax4,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ ( inv1 @ e10 ) ) @ unit1 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( inv1 @ e10 ) @ e10 ) @ unit1 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ ( inv1 @ e11 ) ) @ unit1 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( inv1 @ e11 ) @ e11 ) @ unit1 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ ( inv1 @ e12 ) ) @ unit1 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( inv1 @ e12 ) @ e12 ) @ unit1 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ ( inv1 @ e13 ) ) @ unit1 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ ( inv1 @ e13 ) @ e13 ) @ unit1 ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e13 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e13 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e10 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e11 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e13 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax5,axiom,
    ( mvalid @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e21 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e21 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e21 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e21 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e22 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e22 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e22 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e22 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e23 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e23 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e23 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e23 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e20 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e20 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e20 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e20 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e22 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e22 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e22 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e22 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e23 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e23 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e23 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e23 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e20 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e20 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e20 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e20 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e21 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e21 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e21 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e21 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e22 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e22 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e22 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e22 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e23 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e23 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e23 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e23 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e20 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e20 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e20 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e20 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e21 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e21 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e21 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e21 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e22 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e22 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e22 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e22 ) @ e23 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e23 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e23 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e23 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e23 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax6,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e20 ) @ e20 ) @ ( op2 @ e20 @ ( op2 @ e20 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e20 ) @ e21 ) @ ( op2 @ e20 @ ( op2 @ e20 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e20 ) @ e22 ) @ ( op2 @ e20 @ ( op2 @ e20 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e20 ) @ e23 ) @ ( op2 @ e20 @ ( op2 @ e20 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e21 ) @ e20 ) @ ( op2 @ e20 @ ( op2 @ e21 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e21 ) @ e21 ) @ ( op2 @ e20 @ ( op2 @ e21 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e21 ) @ e22 ) @ ( op2 @ e20 @ ( op2 @ e21 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e21 ) @ e23 ) @ ( op2 @ e20 @ ( op2 @ e21 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e22 ) @ e20 ) @ ( op2 @ e20 @ ( op2 @ e22 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e22 ) @ e21 ) @ ( op2 @ e20 @ ( op2 @ e22 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e22 ) @ e22 ) @ ( op2 @ e20 @ ( op2 @ e22 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e22 ) @ e23 ) @ ( op2 @ e20 @ ( op2 @ e22 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e23 ) @ e20 ) @ ( op2 @ e20 @ ( op2 @ e23 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e23 ) @ e21 ) @ ( op2 @ e20 @ ( op2 @ e23 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e23 ) @ e22 ) @ ( op2 @ e20 @ ( op2 @ e23 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e20 @ e23 ) @ e23 ) @ ( op2 @ e20 @ ( op2 @ e23 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e20 ) @ e20 ) @ ( op2 @ e21 @ ( op2 @ e20 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e20 ) @ e21 ) @ ( op2 @ e21 @ ( op2 @ e20 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e20 ) @ e22 ) @ ( op2 @ e21 @ ( op2 @ e20 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e20 ) @ e23 ) @ ( op2 @ e21 @ ( op2 @ e20 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e21 ) @ e20 ) @ ( op2 @ e21 @ ( op2 @ e21 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e21 ) @ e21 ) @ ( op2 @ e21 @ ( op2 @ e21 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e21 ) @ e22 ) @ ( op2 @ e21 @ ( op2 @ e21 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e21 ) @ e23 ) @ ( op2 @ e21 @ ( op2 @ e21 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e22 ) @ e20 ) @ ( op2 @ e21 @ ( op2 @ e22 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e22 ) @ e21 ) @ ( op2 @ e21 @ ( op2 @ e22 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e22 ) @ e22 ) @ ( op2 @ e21 @ ( op2 @ e22 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e22 ) @ e23 ) @ ( op2 @ e21 @ ( op2 @ e22 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e23 ) @ e20 ) @ ( op2 @ e21 @ ( op2 @ e23 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e23 ) @ e21 ) @ ( op2 @ e21 @ ( op2 @ e23 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e23 ) @ e22 ) @ ( op2 @ e21 @ ( op2 @ e23 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e21 @ e23 ) @ e23 ) @ ( op2 @ e21 @ ( op2 @ e23 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e20 ) @ e20 ) @ ( op2 @ e22 @ ( op2 @ e20 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e20 ) @ e21 ) @ ( op2 @ e22 @ ( op2 @ e20 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e20 ) @ e22 ) @ ( op2 @ e22 @ ( op2 @ e20 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e20 ) @ e23 ) @ ( op2 @ e22 @ ( op2 @ e20 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e21 ) @ e20 ) @ ( op2 @ e22 @ ( op2 @ e21 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e21 ) @ e21 ) @ ( op2 @ e22 @ ( op2 @ e21 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e21 ) @ e22 ) @ ( op2 @ e22 @ ( op2 @ e21 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e21 ) @ e23 ) @ ( op2 @ e22 @ ( op2 @ e21 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e22 ) @ e20 ) @ ( op2 @ e22 @ ( op2 @ e22 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e22 ) @ e21 ) @ ( op2 @ e22 @ ( op2 @ e22 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e22 ) @ e22 ) @ ( op2 @ e22 @ ( op2 @ e22 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e22 ) @ e23 ) @ ( op2 @ e22 @ ( op2 @ e22 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e23 ) @ e20 ) @ ( op2 @ e22 @ ( op2 @ e23 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e23 ) @ e21 ) @ ( op2 @ e22 @ ( op2 @ e23 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e23 ) @ e22 ) @ ( op2 @ e22 @ ( op2 @ e23 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e22 @ e23 ) @ e23 ) @ ( op2 @ e22 @ ( op2 @ e23 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e20 ) @ e20 ) @ ( op2 @ e23 @ ( op2 @ e20 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e20 ) @ e21 ) @ ( op2 @ e23 @ ( op2 @ e20 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e20 ) @ e22 ) @ ( op2 @ e23 @ ( op2 @ e20 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e20 ) @ e23 ) @ ( op2 @ e23 @ ( op2 @ e20 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e21 ) @ e20 ) @ ( op2 @ e23 @ ( op2 @ e21 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e21 ) @ e21 ) @ ( op2 @ e23 @ ( op2 @ e21 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e21 ) @ e22 ) @ ( op2 @ e23 @ ( op2 @ e21 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e21 ) @ e23 ) @ ( op2 @ e23 @ ( op2 @ e21 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e22 ) @ e20 ) @ ( op2 @ e23 @ ( op2 @ e22 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e22 ) @ e21 ) @ ( op2 @ e23 @ ( op2 @ e22 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e22 ) @ e22 ) @ ( op2 @ e23 @ ( op2 @ e22 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e22 ) @ e23 ) @ ( op2 @ e23 @ ( op2 @ e22 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e23 ) @ e20 ) @ ( op2 @ e23 @ ( op2 @ e23 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e23 ) @ e21 ) @ ( op2 @ e23 @ ( op2 @ e23 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e23 ) @ e22 ) @ ( op2 @ e23 @ ( op2 @ e23 @ e22 ) ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( op2 @ e23 @ e23 ) @ e23 ) @ ( op2 @ e23 @ ( op2 @ e23 @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax7,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ unit2 @ e20 ) @ e20 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ unit2 ) @ e20 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ unit2 @ e21 ) @ e21 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ unit2 ) @ e21 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ unit2 @ e22 ) @ e22 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ unit2 ) @ e22 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ unit2 @ e23 ) @ e23 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ unit2 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ unit2 @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ unit2 @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ unit2 @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ unit2 @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax8,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ ( inv2 @ e20 ) ) @ unit2 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( inv2 @ e20 ) @ e20 ) @ unit2 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ ( inv2 @ e21 ) ) @ unit2 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( inv2 @ e21 ) @ e21 ) @ unit2 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ ( inv2 @ e22 ) ) @ unit2 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( inv2 @ e22 ) @ e22 ) @ unit2 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ ( inv2 @ e23 ) ) @ unit2 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ ( inv2 @ e23 ) @ e23 ) @ unit2 ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e23 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e23 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax9,axiom,
    ( mvalid @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ unit1 ) @ unit1 ) ) )).

thf(ax10,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ ( inv1 @ e10 ) ) @ e10 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ ( inv1 @ e11 ) ) @ e11 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ ( inv1 @ e12 ) ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ ( inv1 @ e13 ) ) @ e13 ) ) ) ) ) )).

thf(ax11,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e10 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e11 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e13 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e10 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e11 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e13 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e10 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e11 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e13 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e10 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e11 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ e13 ) ) ) ) @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e13 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e13 ) @ e13 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax12,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ ( inv1 @ e11 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ ( inv1 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e10 ) @ ( inv1 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ ( inv1 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e11 ) @ ( inv1 @ e13 ) ) ) ) ) @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv1 @ e12 ) @ ( inv1 @ e13 ) ) ) ) ) ) ) ) ) ) )).

thf(ax13,axiom,
    ( mvalid @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ unit2 ) @ unit2 ) ) )).

thf(ax14,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ ( inv2 @ e20 ) ) @ e20 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ ( inv2 @ e21 ) ) @ e21 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ ( inv2 @ e22 ) ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ ( inv2 @ e23 ) ) @ e23 ) ) ) ) ) )).

thf(ax15,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ e23 ) ) ) ) @ ( mbox_s4 @ ( mimplies @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e23 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax16,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ ( inv2 @ e21 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ ( inv2 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e20 ) @ ( inv2 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ ( inv2 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e21 ) @ ( inv2 @ e23 ) ) ) ) ) @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( inv2 @ e22 ) @ ( inv2 @ e23 ) ) ) ) ) ) ) ) ) ) )).

thf(ax17,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ ( op1 @ e11 @ e10 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ ( op1 @ e12 @ e10 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ ( op1 @ e13 @ e10 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e10 ) @ ( op1 @ e12 @ e10 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e10 ) @ ( op1 @ e13 @ e10 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e10 ) @ ( op1 @ e13 @ e10 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e11 ) @ ( op1 @ e11 @ e11 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e11 ) @ ( op1 @ e12 @ e11 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e11 ) @ ( op1 @ e13 @ e11 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ ( op1 @ e12 @ e11 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ ( op1 @ e13 @ e11 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e11 ) @ ( op1 @ e13 @ e11 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e12 ) @ ( op1 @ e11 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e12 ) @ ( op1 @ e12 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e12 ) @ ( op1 @ e13 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e12 ) @ ( op1 @ e12 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e12 ) @ ( op1 @ e13 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e12 ) @ ( op1 @ e13 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e13 ) @ ( op1 @ e11 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e13 ) @ ( op1 @ e12 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e13 ) @ ( op1 @ e13 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e13 ) @ ( op1 @ e12 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e13 ) @ ( op1 @ e13 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e13 ) @ ( op1 @ e13 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ ( op1 @ e10 @ e11 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ ( op1 @ e10 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ ( op1 @ e10 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e11 ) @ ( op1 @ e10 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e11 ) @ ( op1 @ e10 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e12 ) @ ( op1 @ e10 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e10 ) @ ( op1 @ e11 @ e11 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e10 ) @ ( op1 @ e11 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e10 ) @ ( op1 @ e11 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ ( op1 @ e11 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ ( op1 @ e11 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e12 ) @ ( op1 @ e11 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e10 ) @ ( op1 @ e12 @ e11 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e10 ) @ ( op1 @ e12 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e10 ) @ ( op1 @ e12 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e11 ) @ ( op1 @ e12 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e11 ) @ ( op1 @ e12 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e12 ) @ ( op1 @ e12 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e10 ) @ ( op1 @ e13 @ e11 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e10 ) @ ( op1 @ e13 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e10 ) @ ( op1 @ e13 @ e13 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e11 ) @ ( op1 @ e13 @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e11 ) @ ( op1 @ e13 @ e13 ) ) ) ) ) @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e12 ) @ ( op1 @ e13 @ e13 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax18,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ ( op2 @ e21 @ e20 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ ( op2 @ e22 @ e20 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ ( op2 @ e23 @ e20 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e20 ) @ ( op2 @ e22 @ e20 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e20 ) @ ( op2 @ e23 @ e20 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e20 ) @ ( op2 @ e23 @ e20 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e21 ) @ ( op2 @ e21 @ e21 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e21 ) @ ( op2 @ e22 @ e21 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e21 ) @ ( op2 @ e23 @ e21 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ ( op2 @ e22 @ e21 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ ( op2 @ e23 @ e21 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e21 ) @ ( op2 @ e23 @ e21 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e22 ) @ ( op2 @ e21 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e22 ) @ ( op2 @ e22 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e22 ) @ ( op2 @ e23 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e22 ) @ ( op2 @ e22 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e22 ) @ ( op2 @ e23 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e22 ) @ ( op2 @ e23 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e23 ) @ ( op2 @ e21 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e23 ) @ ( op2 @ e22 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e23 ) @ ( op2 @ e23 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e23 ) @ ( op2 @ e22 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e23 ) @ ( op2 @ e23 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e23 ) @ ( op2 @ e23 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ ( op2 @ e20 @ e21 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ ( op2 @ e20 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ ( op2 @ e20 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e21 ) @ ( op2 @ e20 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e21 ) @ ( op2 @ e20 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e22 ) @ ( op2 @ e20 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e20 ) @ ( op2 @ e21 @ e21 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e20 ) @ ( op2 @ e21 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e20 ) @ ( op2 @ e21 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ ( op2 @ e21 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ ( op2 @ e21 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e22 ) @ ( op2 @ e21 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e20 ) @ ( op2 @ e22 @ e21 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e20 ) @ ( op2 @ e22 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e20 ) @ ( op2 @ e22 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e21 ) @ ( op2 @ e22 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e21 ) @ ( op2 @ e22 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e22 ) @ ( op2 @ e22 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e20 ) @ ( op2 @ e23 @ e21 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e20 ) @ ( op2 @ e23 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e20 ) @ ( op2 @ e23 @ e23 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e21 ) @ ( op2 @ e23 @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e21 ) @ ( op2 @ e23 @ e23 ) ) ) ) ) @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e22 ) @ ( op2 @ e23 @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax19,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e10 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e10 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e10 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e11 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e11 @ e13 ) ) ) ) @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e12 @ e13 ) ) ) ) ) ) ) ) ) )).

thf(ax20,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e20 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e20 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e20 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e21 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e21 @ e23 ) ) ) ) @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e22 @ e23 ) ) ) ) ) ) ) ) ) )).

thf(ax21,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e10 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e10 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e10 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e10 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e11 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e11 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e11 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e11 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e12 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e12 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e12 @ e22 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e12 @ e23 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e13 @ e20 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e13 @ e21 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e13 @ e22 ) ) ) ) @ ( mbox_s4 @ ( mnot @ ( mbox_s4 @ ( qmltpeq @ e13 @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

thf(ax22,axiom,
    ( mvalid @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ e10 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ e10 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e12 ) @ e10 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e13 ) @ e10 ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ e11 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ e11 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e12 ) @ e11 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e13 ) @ e11 ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ e12 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ e12 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e12 ) @ e12 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e13 ) @ e12 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e10 @ e10 ) @ e13 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e11 @ e11 ) @ e13 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e12 @ e12 ) @ e13 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op1 @ e13 @ e13 ) @ e13 ) ) ) ) ) ) ) ) )).

thf(ax23,axiom,
    ( mvalid @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ e20 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ e20 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e22 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e23 ) @ e20 ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ e21 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ e21 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e22 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e23 ) @ e21 ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ e22 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ e22 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e22 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e23 ) @ e22 ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e20 @ e20 ) @ e23 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e21 @ e21 ) @ e23 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e22 @ e22 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( op2 @ e23 @ e23 ) @ e23 ) ) ) ) ) ) ) ) )).

thf(ax24,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ e10 @ ( op1 @ e13 @ e13 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ e11 @ ( op1 @ e12 @ e13 ) ) ) ) )).

thf(ax25,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ e20 @ ( op2 @ e23 @ e23 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ e21 @ ( op2 @ e22 @ e23 ) ) ) ) )).

thf(ax26,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e12 ) @ e20 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e13 ) @ e21 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e10 ) @ ( op2 @ e21 @ e21 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e11 ) @ ( op2 @ e20 @ e21 ) ) ) ) ) ) )).

thf(ax27,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e12 ) @ e20 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e13 ) @ e22 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e10 ) @ ( op2 @ e22 @ e22 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e11 ) @ ( op2 @ e20 @ e22 ) ) ) ) ) ) )).

thf(ax28,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e12 ) @ e20 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e13 ) @ e23 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e10 ) @ ( op2 @ e23 @ e23 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e11 ) @ ( op2 @ e20 @ e23 ) ) ) ) ) ) )).

thf(ax29,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e12 ) @ e21 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e13 ) @ e20 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e10 ) @ ( op2 @ e20 @ e20 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e11 ) @ ( op2 @ e21 @ e20 ) ) ) ) ) ) )).

thf(ax30,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e12 ) @ e21 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e13 ) @ e22 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e10 ) @ ( op2 @ e22 @ e22 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e11 ) @ ( op2 @ e21 @ e22 ) ) ) ) ) ) )).

thf(ax31,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e12 ) @ e21 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e13 ) @ e23 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e10 ) @ ( op2 @ e23 @ e23 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e11 ) @ ( op2 @ e21 @ e23 ) ) ) ) ) ) )).

thf(ax32,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e12 ) @ e22 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e13 ) @ e20 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e10 ) @ ( op2 @ e20 @ e20 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e11 ) @ ( op2 @ e22 @ e20 ) ) ) ) ) ) )).

thf(ax33,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e12 ) @ e22 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e13 ) @ e21 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e10 ) @ ( op2 @ e21 @ e21 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e11 ) @ ( op2 @ e22 @ e21 ) ) ) ) ) ) )).

thf(ax34,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e12 ) @ e22 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e13 ) @ e23 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e10 ) @ ( op2 @ e23 @ e23 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e11 ) @ ( op2 @ e22 @ e23 ) ) ) ) ) ) )).

thf(ax35,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e12 ) @ e23 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e13 ) @ e20 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e10 ) @ ( op2 @ e20 @ e20 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e11 ) @ ( op2 @ e23 @ e20 ) ) ) ) ) ) )).

thf(ax36,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e12 ) @ e23 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e13 ) @ e21 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e10 ) @ ( op2 @ e21 @ e21 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e11 ) @ ( op2 @ e23 @ e21 ) ) ) ) ) ) )).

thf(ax37,axiom,
    ( mvalid @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e12 ) @ e23 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e13 ) @ e22 ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e10 ) @ ( op2 @ e22 @ e22 ) ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e11 ) @ ( op2 @ e23 @ e22 ) ) ) ) ) ) )).

thf(co1,conjecture,
    ( mvalid @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h1 @ e10 ) @ ( h1 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h1 @ e10 ) @ ( h1 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h1 @ e10 ) @ ( h1 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h1 @ e10 ) @ ( h1 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h1 @ e11 ) @ ( h1 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h1 @ e11 ) @ ( h1 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h1 @ e11 ) @ ( h1 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h1 @ e11 ) @ ( h1 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h1 @ e12 ) @ ( h1 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h1 @ e12 ) @ ( h1 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h1 @ e12 ) @ ( h1 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h1 @ e12 ) @ ( h1 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h1 @ e13 ) @ ( h1 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h1 @ e13 ) @ ( h1 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h1 @ e13 ) @ ( h1 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h1 @ e13 ) @ ( h1 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h1 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h2 @ e10 ) @ ( h2 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h2 @ e10 ) @ ( h2 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h2 @ e10 ) @ ( h2 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h2 @ e10 ) @ ( h2 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h2 @ e11 ) @ ( h2 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h2 @ e11 ) @ ( h2 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h2 @ e11 ) @ ( h2 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h2 @ e11 ) @ ( h2 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h2 @ e12 ) @ ( h2 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h2 @ e12 ) @ ( h2 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h2 @ e12 ) @ ( h2 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h2 @ e12 ) @ ( h2 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h2 @ e13 ) @ ( h2 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h2 @ e13 ) @ ( h2 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h2 @ e13 ) @ ( h2 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h2 @ e13 ) @ ( h2 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h2 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h3 @ e10 ) @ ( h3 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h3 @ e10 ) @ ( h3 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h3 @ e10 ) @ ( h3 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h3 @ e10 ) @ ( h3 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h3 @ e11 ) @ ( h3 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h3 @ e11 ) @ ( h3 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h3 @ e11 ) @ ( h3 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h3 @ e11 ) @ ( h3 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h3 @ e12 ) @ ( h3 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h3 @ e12 ) @ ( h3 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h3 @ e12 ) @ ( h3 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h3 @ e12 ) @ ( h3 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h3 @ e13 ) @ ( h3 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h3 @ e13 ) @ ( h3 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h3 @ e13 ) @ ( h3 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h3 @ e13 ) @ ( h3 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h3 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h4 @ e10 ) @ ( h4 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h4 @ e10 ) @ ( h4 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h4 @ e10 ) @ ( h4 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h4 @ e10 ) @ ( h4 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h4 @ e11 ) @ ( h4 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h4 @ e11 ) @ ( h4 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h4 @ e11 ) @ ( h4 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h4 @ e11 ) @ ( h4 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h4 @ e12 ) @ ( h4 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h4 @ e12 ) @ ( h4 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h4 @ e12 ) @ ( h4 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h4 @ e12 ) @ ( h4 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h4 @ e13 ) @ ( h4 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h4 @ e13 ) @ ( h4 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h4 @ e13 ) @ ( h4 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h4 @ e13 ) @ ( h4 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h4 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h5 @ e10 ) @ ( h5 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h5 @ e10 ) @ ( h5 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h5 @ e10 ) @ ( h5 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h5 @ e10 ) @ ( h5 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h5 @ e11 ) @ ( h5 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h5 @ e11 ) @ ( h5 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h5 @ e11 ) @ ( h5 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h5 @ e11 ) @ ( h5 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h5 @ e12 ) @ ( h5 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h5 @ e12 ) @ ( h5 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h5 @ e12 ) @ ( h5 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h5 @ e12 ) @ ( h5 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h5 @ e13 ) @ ( h5 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h5 @ e13 ) @ ( h5 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h5 @ e13 ) @ ( h5 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h5 @ e13 ) @ ( h5 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h5 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h6 @ e10 ) @ ( h6 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h6 @ e10 ) @ ( h6 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h6 @ e10 ) @ ( h6 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h6 @ e10 ) @ ( h6 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h6 @ e11 ) @ ( h6 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h6 @ e11 ) @ ( h6 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h6 @ e11 ) @ ( h6 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h6 @ e11 ) @ ( h6 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h6 @ e12 ) @ ( h6 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h6 @ e12 ) @ ( h6 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h6 @ e12 ) @ ( h6 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h6 @ e12 ) @ ( h6 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h6 @ e13 ) @ ( h6 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h6 @ e13 ) @ ( h6 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h6 @ e13 ) @ ( h6 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h6 @ e13 ) @ ( h6 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h6 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h7 @ e10 ) @ ( h7 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h7 @ e10 ) @ ( h7 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h7 @ e10 ) @ ( h7 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h7 @ e10 ) @ ( h7 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h7 @ e11 ) @ ( h7 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h7 @ e11 ) @ ( h7 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h7 @ e11 ) @ ( h7 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h7 @ e11 ) @ ( h7 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h7 @ e12 ) @ ( h7 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h7 @ e12 ) @ ( h7 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h7 @ e12 ) @ ( h7 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h7 @ e12 ) @ ( h7 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h7 @ e13 ) @ ( h7 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h7 @ e13 ) @ ( h7 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h7 @ e13 ) @ ( h7 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h7 @ e13 ) @ ( h7 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h7 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h8 @ e10 ) @ ( h8 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h8 @ e10 ) @ ( h8 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h8 @ e10 ) @ ( h8 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h8 @ e10 ) @ ( h8 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h8 @ e11 ) @ ( h8 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h8 @ e11 ) @ ( h8 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h8 @ e11 ) @ ( h8 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h8 @ e11 ) @ ( h8 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h8 @ e12 ) @ ( h8 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h8 @ e12 ) @ ( h8 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h8 @ e12 ) @ ( h8 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h8 @ e12 ) @ ( h8 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h8 @ e13 ) @ ( h8 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h8 @ e13 ) @ ( h8 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h8 @ e13 ) @ ( h8 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h8 @ e13 ) @ ( h8 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h8 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h9 @ e10 ) @ ( h9 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h9 @ e10 ) @ ( h9 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h9 @ e10 ) @ ( h9 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h9 @ e10 ) @ ( h9 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h9 @ e11 ) @ ( h9 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h9 @ e11 ) @ ( h9 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h9 @ e11 ) @ ( h9 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h9 @ e11 ) @ ( h9 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h9 @ e12 ) @ ( h9 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h9 @ e12 ) @ ( h9 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h9 @ e12 ) @ ( h9 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h9 @ e12 ) @ ( h9 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h9 @ e13 ) @ ( h9 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h9 @ e13 ) @ ( h9 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h9 @ e13 ) @ ( h9 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h9 @ e13 ) @ ( h9 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h9 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h10 @ e10 ) @ ( h10 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h10 @ e10 ) @ ( h10 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h10 @ e10 ) @ ( h10 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h10 @ e10 ) @ ( h10 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h10 @ e11 ) @ ( h10 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h10 @ e11 ) @ ( h10 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h10 @ e11 ) @ ( h10 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h10 @ e11 ) @ ( h10 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h10 @ e12 ) @ ( h10 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h10 @ e12 ) @ ( h10 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h10 @ e12 ) @ ( h10 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h10 @ e12 ) @ ( h10 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h10 @ e13 ) @ ( h10 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h10 @ e13 ) @ ( h10 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h10 @ e13 ) @ ( h10 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h10 @ e13 ) @ ( h10 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h10 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) @ ( mor @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h11 @ e10 ) @ ( h11 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h11 @ e10 ) @ ( h11 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h11 @ e10 ) @ ( h11 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h11 @ e10 ) @ ( h11 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h11 @ e11 ) @ ( h11 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h11 @ e11 ) @ ( h11 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h11 @ e11 ) @ ( h11 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h11 @ e11 ) @ ( h11 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h11 @ e12 ) @ ( h11 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h11 @ e12 ) @ ( h11 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h11 @ e12 ) @ ( h11 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h11 @ e12 ) @ ( h11 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h11 @ e13 ) @ ( h11 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h11 @ e13 ) @ ( h11 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h11 @ e13 ) @ ( h11 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h11 @ e13 ) @ ( h11 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h11 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e10 @ e10 ) ) @ ( op2 @ ( h12 @ e10 ) @ ( h12 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e10 @ e11 ) ) @ ( op2 @ ( h12 @ e10 ) @ ( h12 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e10 @ e12 ) ) @ ( op2 @ ( h12 @ e10 ) @ ( h12 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e10 @ e13 ) ) @ ( op2 @ ( h12 @ e10 ) @ ( h12 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e11 @ e10 ) ) @ ( op2 @ ( h12 @ e11 ) @ ( h12 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e11 @ e11 ) ) @ ( op2 @ ( h12 @ e11 ) @ ( h12 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e11 @ e12 ) ) @ ( op2 @ ( h12 @ e11 ) @ ( h12 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e11 @ e13 ) ) @ ( op2 @ ( h12 @ e11 ) @ ( h12 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e12 @ e10 ) ) @ ( op2 @ ( h12 @ e12 ) @ ( h12 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e12 @ e11 ) ) @ ( op2 @ ( h12 @ e12 ) @ ( h12 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e12 @ e12 ) ) @ ( op2 @ ( h12 @ e12 ) @ ( h12 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e12 @ e13 ) ) @ ( op2 @ ( h12 @ e12 ) @ ( h12 @ e13 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e13 @ e10 ) ) @ ( op2 @ ( h12 @ e13 ) @ ( h12 @ e10 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e13 @ e11 ) ) @ ( op2 @ ( h12 @ e13 ) @ ( h12 @ e11 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e13 @ e12 ) ) @ ( op2 @ ( h12 @ e13 ) @ ( h12 @ e12 ) ) ) ) @ ( mand @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ ( op1 @ e13 @ e13 ) ) @ ( op2 @ ( h12 @ e13 ) @ ( h12 @ e13 ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e10 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e11 ) @ e20 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e12 ) @ e20 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e13 ) @ e20 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e10 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e11 ) @ e21 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e12 ) @ e21 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e13 ) @ e21 ) ) ) ) ) @ ( mand @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e10 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e11 ) @ e22 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e12 ) @ e22 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e13 ) @ e22 ) ) ) ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e10 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e11 ) @ e23 ) ) @ ( mor @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e12 ) @ e23 ) ) @ ( mbox_s4 @ ( qmltpeq @ ( h12 @ e13 ) @ e23 ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) ) )).

%------------------------------------------------------------------------------
