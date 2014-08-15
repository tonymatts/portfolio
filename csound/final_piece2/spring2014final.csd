<CsoundSynthesizer>
<CsOptions>
</CsOptions>
<CsInstruments>

sr = 44100;Sets the audio sampling rate
ksmps = 320;Sets the number of samples in a control period
nchnls = 2;Sets the number of channels of audio output
0dbfs = 1.0;Sets the value of 0 decibels using full scale amplitude

; synth waveform
giwave  ftgen 1, 0, 1024, 10, 1, 1, 1, 1
; blending window
giblend ftgen 2, 0, 1024, -19, 1, 0.5, 270, 0.5

ga1 init 0;reverb_i11
ga2 init 0;flanger_i3 & i_33

;////////////////////////////////////////////////////////////////////////
; movement one
;////////////////////////////////////////////////////////////////////////
instr 1
	ifn = 3;f table #
	ixmode = 1
	
	kndx phasor p4
	kfrq table kndx, ifn, ixmode
	asig poscil .01, kfrq, 4  ;re-synthesize with sine
	outs asig, asig
endin

instr 2
	insnd   = 10 
	ibasfrq = 44100 / ftlen(insnd) ;use original sample rate of insnd file 

	kamp   expseg .001, p3/2, .1, p3/2, .01
	kpitch line ibasfrq, p3, ibasfrq * .8 
	kdens  line 600, p3, 100 
	kaoff  line 0, p3, .1
	kpoff  line 0, p3, ibasfrq * .5 
	kgdur  line .4, p3, .01
	imaxgdur =  .5

	asigL  grain kamp, kpitch, kdens, kaoff, kpoff, kgdur, insnd, 5, imaxgdur, 0.0 
	asigR  grain kamp, kpitch, kdens, kaoff, kpoff, kgdur, insnd, 5, imaxgdur, 0.0
	outs asigL, asigR	
endin

;////////////////////////////////////////////////////////////////////////
; movement two
;////////////////////////////////////////////////////////////////////////
instr 3
	kamp = .5 ;max is 1
	gkfreq = p4
	kfback = .8

	adel line 0, p3*2, 0.01
	a1 mpulse kamp, gkfreq	 
	afl flanger a1, adel, kfback
 	out afl, afl
 	
	ga2 = ga2 + a1
endin

instr 33
	kamp = .6 ;max is 1
	gkfreq = p4
	kfback = .6

	adel line 0.01, p3*2, 0
	a1 mpulse kamp, gkfreq	 
	afl flanger a1, adel, kfback
 	out afl, afl
 	
	ga2 = ga2 + a1
endin

instr 4
	kdclk linseg  0, 0.01, 1, p3-0.02, 1, 0.01, 0
	kcx line    0.1, p3, 1.9
	krx linseg  0.1, p3/2, 0.5, p3/2, 0.1
	kpch line    cpspch(p4), p3, p5 * cpspch(p4)
	a1 wterrain    .1, kpch, kcx, kcx, -krx, krx, p6, p7
	a1 dcblock a1
	out     a1*kdclk
	
	ga1 += a1
endin

;////////////////////////////////////////////////////////////////////////
; movement three
;////////////////////////////////////////////////////////////////////////
instr 5
	kamp = .2
	kbrite = 0
	ibasfreq = 50
	ioctcnt = 6

	;Change ktone linearly from 0 to 1, 
	;over the period defined by p3.
	ktone line 0, p3, 1
	;Risset's glissando
	asig hsboscil kamp, ktone, kbrite, ibasfreq, giwave, giblend, ioctcnt	     
	outs asig, asig
	
	ga1 += asig	
endin

instr 6
	kamp = .2
	kbrite = 0
	ibasfreq = 50
	ioctcnt = 6

	;Change ktone linearly from 0 to 1, 
	;over the period defined by p3.
	ktone line 1, p3, 0
	;Risset's glissando
	asig hsboscil kamp, ktone, kbrite, ibasfreq, giwave, giblend, ioctcnt 
	outs asig, asig
	
	ga1 += asig
endin

instr 7
	kbeta linseg 0, p3/4, 2, p3/4, 0, p3*0.1, 2, p3*0.15, 0
	seed  20120124
	aout  fractalnoise 0.002, kbeta 
	outs  aout, aout
endin

;////////////////////////////////////////////////////////////////////////
; instrument sound design
;////////////////////////////////////////////////////////////////////////
instr 11
	arev reverb ga1, 1.5
	outs arev, arev	
	
	ga1 = 0				
endin

</CsInstruments>
<CsScore>
;////////////////////////////////////////
; F tables
;////////////////////////////////////////
f3 0 1025 -7 200 1024 2000;mov1_i1
f4 0 16384 10 1;mov1_i1

f5  0 512  20 2;mov1_i2
f10 0 16384 1  "claps.aif" 0 0 0;mov1_i2

f11 0 8192 10      1 0 0.33 0 0.2 0 0.14 0 0.11;mov2_i4
f12 0 4096 10      1;mov2_i4

;////////////////////////////////////////
; movement one - instrument one
;////////////////////////////////////////
;i1 5 7 1
;i1 5 14 89
;i1 17 2 34
;i1 19 1 55
;i1 20 2 89

;i1 22 12 55
;i1 24 11 34
i1 25 5 89

i1 129 2 5
i1 130 3 3
i1 132 2 8
i1 133 4 2
i1 136 3 5
;
;i1 38 10 5
;i1 39 9 2
;i1 41 7 8
;i1 42 6 3
;i1 45 3 5
;
;i1 50 10 80
;i1 50 10 70
;
;i1 59 7 3
;i1 59 14 55
;
;i1 75 2 45
;i1 77 5 40
;i1 82 1 35
;i1 83 4 30
;i1 87 8 25
;i1 95 2 20
;i1 97 3 15
;i1 100 5 10
;i1 105 2 5
;
i1 107 11 89
;i1 112 6 90

;////////////////////////////////////////
; movement one - instrument two
;////////////////////////////////////////
;i2 0 15
;i2 5 30
;i2 15 45
i2 45 3
i2 47 6
i2 48 2
i2 49 3
i2 52 100

;////////////////////////////////////////
; movement two - instrument three
;////////////////////////////////////////
i3 0 18 .1

i3 110 6 .09
i3 111 5 .1

i3 116 10 .08
i3 116.5 15.5 .09
i3 117.5 13.5 .1

i3 126 10 .4
i3 126.5 9.5 .3
i3 127.5 8.5 .2

i3 136 10 .2
i3 136.5 9.5 .3
i3 137.5 8.5 .4

i3 146 10 .5
i3 146.5 9.5 .6
i3 147.5 8.5 .7

i3 156 10 .7
i3 156.5 9.5 .6
i3 157.5 8.5 .5

i3 166 10 .35
i3 166.5 9.5 .85
i3 167.5 8.5 .95

i3 176 10 .9
i3 176.5 9.5 .2
i3 177.5 8.5 .3

i3 186 10 .25
i3 186.5 9.5 .6
i3 187.5 8.5 .6

i3 196 15 .75
i3 196.5 15.5 .15
i3 197.5 13.5 .1

;////////////////////////////////////////
; movement two - instrument three
;////////////////////////////////////////
;i33 25 25 .1
i33 118 6 .09
i33 110 5 .1

i33 117 10 .08
i33 117.5 15.5 .09
i33 119.5 13.5 .1

i33 125 10 .4
i33 127.5 9.5 .3
i33 129.5 8.5 .2

i33 131 10 .2
i33 133.5 9.5 .3
i33 139.5 8.5 .4

i33 142 10 .5
i33 143.5 9.5 .6
i33 148.5 8.5 .7

i33 151 10 .7
i33 152.5 9.5 .6
i33 154.5 8.5 .5

i33 168 10 .35
i33 169.5 9.5 .85
i33 170.5 8.5 .95

i33 179 10 .9
i33 179.5 9.5 .2
i33 185.5 8.5 .3

i33 187 10 .25
i33 189.5 9.5 .6
i33 190.5 8.5 .6

i33 193 15 .75
i33 194.5 15.5 .15
i33 195.5 13.5 .1

;////////////////////////////////////////
; movement two - instrument four
;////////////////////////////////////////
i4 0 24 3.00 11 11 11
i4 10 105 2.07 12 12 12

i4 110 20 3.00 11 12 12
;i4 125 20 3.05 11 12 12
;i4 140 20 3.03 11 12 12

i4 55 20 3.00 11 12 12
i4 70 20 3.03 11 12 12
i4 85 20 3.05 11 12 12
;
;i4 100 29 3.00 11 12 12
;i4 100 30 4.00 11 12 12
;
;i4 125 10 4.00 11 12 12
;i4 130 10 3.00 11 12 12

;////////////////////////////////////////
; movement three - instrument five
;////////////////////////////////////////
;i5 1 10
i5 22 80
i5 70 70

;////////////////////////////////////////
; movement three - instrument six
;////////////////////////////////////////
;i6 1 10
i6 22 90
i6 100 30

;////////////////////////////////////////
; movement three - instrument seven
;////////////////////////////////////////
i7 0 211

;////////////////////////////////////////
; sound design - instrument eleven
;////////////////////////////////////////
i11 0 160
e;end
</CsScore>
</CsoundSynthesizer>
<bsbPanel>
 <label>Widgets</label>
 <objectName/>
 <x>100</x>
 <y>100</y>
 <width>320</width>
 <height>240</height>
 <visible>true</visible>
 <uuid/>
 <bgcolor mode="nobackground">
  <r>255</r>
  <g>255</g>
  <b>255</b>
 </bgcolor>
</bsbPanel>
<bsbPresets>
</bsbPresets>
