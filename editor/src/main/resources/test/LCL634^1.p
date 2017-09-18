%------------------------------------------------------------------------------
% File     : LCL634^1 : TPTP v7.0.0. Bugfixed v5.2.0.
% Domain   : Logical Calculi
% Problem  : Goedel's ontological argument on the existence of God
% Version  : [Ben08] axioms.
% English  :

% Refs     : [Fit00] Fitting (2000), Higher-Order Modal Logic - A Sketch
%          : [Ben08] Benzmueller (2008), Email to G. Sutcliffe
% Source   : [Ben08]
% Names    : Fitting-HOLML-Ex-God-alternative-b [Ben08]

% Status   : CounterSatisfiable
% Rating   : 0.67 v5.4.0, 1.00 v5.2.0
% Syntax   : Number of formulae    :   26 (   0 unit;  12 type;   9 defn)
%            Number of atoms       :   95 (   9 equality;  47 variable)
%            Maximal formula depth :   13 (   6 average)
%            Number of connectives :   64 (   1   ~;   1   |;   1   &;  58   @)
%                                         (   0 <=>;   3  =>;   0  <=;   0 <~>)
%                                         (   0  ~|;   0  ~&)
%            Number of type conns  :   69 (  69   >;   0   *;   0   +;   0  <<)
%            Number of symbols     :   15 (  12   :;   0   =)
%            Number of variables   :   34 (   0 sgn;  10   !;   0   ?;  24   ^)
%                                         (  34   :;   0  !>;   0  ?*)
%                                         (   0  @-;   0  @+)
% SPC      : TH0_CSA_EQU_NAR

% Comments : 
% Bugfixes : v4.0.0 - Complete overhaul.
%          : v5.2.0 - Added missing types
%------------------------------------------------------------------------------
%----Some higher-order multimodal operators are needed
%----Base type individuals; corresponds to type 0 in [Fit00]
thf(individuals_decl,type,(
    individuals: $tType )).

thf(e_type,type,(
    e: ( individuals > $i > $o ) > individuals > $i > $o )).

thf(g_type,type,(
    g: individuals > $i > $o )).

thf(mall_ind_type,type,(
    mall_ind: ( individuals > $i > $o ) > $i > $o )).

thf(mall_indset_type,type,(
    mall_indset: ( ( individuals > $i > $o ) > $i > $o ) > $i > $o )).

thf(mbox_type,type,(
    mbox: ( $i > $i > $o ) > ( $i > $o ) > $i > $o )).

thf(mimplies_type,type,(
    mimplies: ( $i > $o ) > ( $i > $o ) > $i > $o )).

thf(mnot_type,type,(
    mnot: ( $i > $o ) > $i > $o )).

thf(mor_type,type,(
    mor: ( $i > $o ) > ( $i > $o ) > $i > $o )).

thf(mvalid_type,type,(
    mvalid: ( $i > $o ) > $o )).

%----mnot corresponds to set complement
thf(mnot,definition,
    ( mnot
    = ( ^ [Phi: $i > $o,W: $i] :
          ~ ( Phi @ W ) ) )).

thf(mor,definition,
    ( mor
    = ( ^ [Phi: $i > $o,Psi: $i > $o,W: $i] :
          ( ( Phi @ W )
          | ( Psi @ W ) ) ) )).

%----mimplies defined via mnot and mor
thf(mimplies,definition,
    ( mimplies
    = ( ^ [Phi: $i > $o,Psi: $i > $o] :
          ( mor @ ( mnot @ Phi ) @ Psi ) ) )).

%----mbox
thf(mbox,definition,
    ( mbox
    = ( ^ [R: $i > $i > $o,Phi: $i > $o,W: $i] :
        ! [U: $i] :
          ( ( R @ W @ U )
         => ( Phi @ U ) ) ) )).

%----mall_ind (for individuals)
thf(mall_ind,definition,
    ( mall_ind
    = ( ^ [Phi: individuals > $i > $o,W: $i] :
        ! [X: individuals] :
          ( Phi @ X @ W ) ) )).

%----mall_indset (for sets of individuals)
thf(mall_indset,definition,
    ( mall_indset
    = ( ^ [Phi: ( individuals > $i > $o ) > $i > $o,W: $i] :
        ! [X: individuals > $i > $o] :
          ( Phi @ X @ W ) ) )).

%----Validity of a multi modal logic formula can now be encoded as
thf(mvalid,definition,
    ( mvalid
    = ( ^ [Phi: $i > $o] :
        ! [W: $i] :
          ( Phi @ W ) ) )).

%----The encoding of the example
%----r is an S5 accessibility relation: reflexive, transitive and symmetric
thf(r_type,type,(
    r: $i > $i > $o )).

thf(r_refl,axiom,(
    ! [X: $i] :
      ( r @ X @ X ) )).

thf(r_trans,axiom,(
    ! [X: $i,Y: $i,Z: $i] :
      ( ( ( r @ X @ Y )
        & ( r @ Y @ Z ) )
     => ( r @ X @ Z ) ) )).

thf(r_sym,axiom,(
    ! [X: $i,Y: $i] :
      ( ( r @ X @ Y )
     => ( r @ Y @ X ) ) )).

%----The axioms of the example
%----Positiveness: p
thf(p_type,type,(
    p: ( individuals > $i > $o ) > $i > $o )).

thf(positiveness,axiom,
    ( mvalid
    @ ( mall_indset
      @ ^ [X: individuals > $i > $o] :
          ( mimplies @ ( mnot @ ( p @ X ) )
          @ ( p
            @ ^ [Z: individuals] :
                ( mnot @ ( X @ Z ) ) ) ) ) )).

%----Being God: g
thf(g,definition,
    ( g
    = ( ^ [Z: individuals] :
          ( mall_indset
          @ ^ [X: individuals > $i > $o] :
              ( mimplies @ ( p @ X ) @ ( X @ Z ) ) ) ) )).

%----Essence: e
thf(e,definition,
    ( e
    = ( ^ [X: individuals > $i > $o,Z: individuals] :
          ( mall_indset
          @ ^ [Y: individuals > $i > $o] :
              ( mimplies @ ( Y @ Z )
              @ ( mbox @ r
                @ ( mall_ind
                  @ ^ [U: individuals] :
                      ( mimplies @ ( X @ U ) @ ( Y @ U ) ) ) ) ) ) ) )).

%----Conjecture: Being God is the essence of anything that is, in fact, God.
thf(thm,conjecture,
    ( mvalid
    @ ( mall_ind
      @ ^ [Z: individuals] :
          ( mimplies @ ( g @ Z ) @ ( e @ g @ Z ) ) ) )).

%------------------------------------------------------------------------------
