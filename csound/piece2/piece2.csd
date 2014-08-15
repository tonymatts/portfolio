<CsoundSynthesizer>
<CsOptions>
</CsOptions>
<CsInstruments>

sr = 44100
kr = 4410
ksmps = 10
nchnls = 1

gar init 0
gadel init 0
gidtim init 0
gidfback init 0

instr 1

kamp expseg 5000, p3/2, 10000, p3/2, 10000
ipitch = p4
iden = p5
iampoff = p6
ipitoff = p7
kgdur line p10, p3, p11
isft = p8
ienv = p9
islngth = ftlen(isft)
isftt = islngth/sr

	a1 grain kamp, ipitch, iden, iampoff, ipitoff, kgdur, isft, ienv, isftt
	out a1

endin

instr 11

kamp expseg 5000, p3/2, 10000, p3/2, 10000
ipitch = p4
iden = p5
iampoff = p6
ipitoff = p7
kgdur line p10, p3, p11
isft = p8
ienv = p9
islngth = ftlen(isft)
isftt = islngth/sr
agdur line 0, p3, .1
kfback line .1, p3, .9

	a1 grain kamp, ipitch, iden, iampoff, ipitoff, kgdur, isft, ienv, isftt
	afl flanger a1, agdur, kfback
	out afl

endin

instr 3

idur = p3
kamp = p4
ia = p5
ib = p6
kcps = p7
ifn = p8

	k1 line ia, idur, ib
	a2 mpulse 50, k1
	a1 oscili kamp, kcps*a2, ifn 
	out a1
	gadel = gadel * a1

gidtim = p9
gidfback = p10

endin

instr 31

	a1 delay gadel, gidtim
	out a1
	gadel = a1*gidfback

endin

instr 32

idur = p3
kamp = p4
ia = p5
ib = p6
kcps = p7
ifn = p8
agdur line 0, p3, .1
;kfback line .1, p3, .9

	k1 line ia, idur, ib
	a2 mpulse 5000, k1
	k2 oscili .1, p3*2, ifn
	afl flanger a2, agdur, k2  
	out afl

endin
</CsInstruments>
<CsScore>
f1 0 4096 10 1
f2 0 128 10 1
f3 0 4096 7 0 512 1 2 -1 256 1 ;Sawtooth
f5 0 32768 1 "claps.aif" 0 4 0 ; 
a 0 0 30

i1 0 1 15 4 100 1000 5 1 .05 .05
;i1 2 1 15 6 100 1000 5 1 .05 .01
;i1 3 1 15 4 100 1000 5 1 .05 .05
;i1 4 1 15 2 100 1000 5 1 .05 .0005
i1 6 1 15 4 100 1000 5 1 .05 .05
i1 7 13 15 6 100 1000 5 1 .05 .0005
;i1 8 20 15 4 100 1000 5 1 .05 .05
i1 0 30 150 2 1 10000 5 5 .00009 .9

i11 0 1 15 4 100 1000 5 1 .05 .05
i11 2 1 15 6 100 1000 5 1 .05 .01
;i11 3 1 15 4 100 1000 5 1 .05 .05
;i11 4 1 15 2 100 1000 5 1 .05 .0005
i11 6 1 15 4 100 1000 5 1 .05 .05
;i11 7 13 15 6 100 1000 5 1 .05 .0005
i11 8 20 15 4 100 1000 5 1 .05 .05
i11 0 60 150 2 1 10000 5 5 .00009 .9
i11 32 28 150 2 1 10000 5 5 .00009 .9

i2 40 .1 5000 6.02 15.00 2
i2 + . 5000 6.02 -15.00 2
i2 . . 5000 6.02 15.00 2
i2 . . 5000 6.02 -5.020 2
i2 . . 5000 6.02 15.00 2
i2 . . 5000 6.02 -15.02 2
i2 . . 5000 6.02 15.00 2
i2 . . 5000 6.02 -5.00 2
i2 . . 5000 6.02 15.00 2
i2 . . 5000 6.02 -5.00 2
i2 . . 5000 6.02 15.00 2
i2 . . 5000 6.02 -5.00 2


i2 9.8 .1 2000 6.02 -140.00 3
i2 10 .2 2000 6.02 -140.00 3
i2 10.3 .3 2000 6.02 -140.00 3
i2 10.7 .4 2000 6.02 -140.00 3
i2 12.2 .5 2000 6.02 -140.00 2
i2 12.7 .7 2000 6.02 -140.00 2
i2 13.4 .5 2000 6.02 -140.00 2
i2 13.9 .5 2000 6.02 -140.00 2
i2 14.4 .7 2000 6.02 -140.00 2
i2 15.1 .5 2000 6.02 -140.00 2

i21 0 30

;i3 0 60 10000 .5 .09 40 1 .1 .9

i31 0 10

i32 30 3 2000 .001 .0001 840 1
i32 33 2 3000 .0001 .001 840 1
i32 35 3 3000 .001 .0003 840 1
i32 38 8 10 3000 .0003 .03 840 1
;i32 8 2 3000 .00003 .03 840 1
</CsScore>
</CsoundSynthesizer>

<MacOptions>
Version: 3
Render: Real
Ask: Yes
Functions: ioObject
Listing: Window
WindowBounds: 734 442 546 181
CurrentView: io
IOViewEdit: On
Options: -b128 -A -s -m167 -R
</MacOptions>
<MacGUI>
ioView background {32125, 41634, 41120}
ioSlider {266, 7} {20, 98} 0.000000 1.000000 0.122449 amp
ioSlider {10, 29} {239, 22} 100.000000 1000.000000 258.158996 freq
ioGraph {8, 112} {265, 116} table 0.000000 1.000000 
ioListing {279, 112} {266, 266}
ioText {293, 44} {41, 24} label 0.000000 0.00100 "" left "Lucida Grande" 8 {0, 0, 0} {65280, 65280, 65280} background noborder Amp:
ioText {333, 44} {70, 24} display 0.000000 0.00100 "amp" left "Lucida Grande" 8 {0, 0, 0} {65280, 65280, 65280} background noborder 0.1837
ioText {66, 57} {41, 24} label 0.000000 0.00100 "" left "Lucida Grande" 8 {0, 0, 0} {65280, 65280, 65280} background noborder Freq:
ioText {106, 57} {69, 24} display 0.000000 0.00100 "freq" left "Lucida Grande" 8 {0, 0, 0} {65280, 65280, 65280} background noborder 261.9247
ioText {425, 6} {120, 100} label 0.000000 0.00100 "" left "Lucida Grande" 8 {0, 0, 0} {65280, 65280, 65280} nobackground border 
ioText {449, 68} {78, 24} display 0.000000 0.00100 "freqsweep" center "DejaVu Sans" 8 {0, 0, 0} {14080, 31232, 29696} background border 999.6769
ioButton {435, 24} {100, 30} event 1.000000 "Button 1" "Sweep" "/" i1 0 10
ioGraph {8, 233} {266, 147} scope 2.000000 -1.000000 
</MacGUI>

<EventPanel name="" tempo="60.00000000" loop="8.00000000" name="" x="320" y="218" width="596" height="322"> 
 
 
 
 
 
 
 
 
 </EventPanel>