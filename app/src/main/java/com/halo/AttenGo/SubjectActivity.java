package com.halo.AttenGo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SubjectActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView SubNODP,SubjectTitle,SubSTLA,SubSDOL;
    private EditText SubOTP;
    private Button SubMark;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference,SubjectInformation;
    private DatabaseReference TotalAttendenceCount;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        firebaseAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        String str = intent.getStringExtra("Selected Subject");
        TotalAttendenceCount =FirebaseDatabase.getInstance().getReference("TotalAttendenceCount");
        databaseReference = FirebaseDatabase.getInstance().getReference("OTP");
        SubSTLA=(TextView)findViewById(R.id.stla);
        SubSDOL = (TextView)findViewById(R.id.sdol);
        mRef = FirebaseDatabase.getInstance().getReference("Student Info");
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,Activity_Login.class));
        }
        SubNODP=(TextView)findViewById(R.id.snodp);
        SubOTP=(EditText)findViewById(R.id.sotp);
        SubjectTitle=(TextView)findViewById(R.id.subjecttitle);
        SubMark=(Button)findViewById(R.id.saveattendence);
        SubMark.setOnClickListener(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        switch (str){
            case "Chemistry":
                SubjectTitle.setText(str);
                SubjectInformation =FirebaseDatabase.getInstance().getReference("ChemistryInformation");
                SubjectInformation.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        SubSTLA.setText(dataSnapshot.child("TNOL").getValue(String.class));
                        SubSDOL.setText(dataSnapshot.child("NOLIW").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mRef.child(user.getUid()).child("Chemistry").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer counter =dataSnapshot.getValue(Integer.class);
                        SubNODP.setText(counter.toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Physics":
                SubjectTitle.setText(str);
                SubjectInformation =FirebaseDatabase.getInstance().getReference("PhysicsInformation");
                SubjectInformation.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        SubSTLA.setText(dataSnapshot.child("TNOL").getValue(String.class));
                        SubSDOL.setText(dataSnapshot.child("NOLIW").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mRef.child(user.getUid()).child("Physics").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer counter =dataSnapshot.getValue(Integer.class);
                        SubNODP.setText(counter.toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Petroleum":
                SubjectTitle.setText(str);
                SubjectInformation =FirebaseDatabase.getInstance().getReference("PetroleumInformation");
                SubjectInformation.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        SubSTLA.setText(dataSnapshot.child("TNOL").getValue(String.class));
                        SubSDOL.setText(dataSnapshot.child("NOLIW").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mRef.child(user.getUid()).child("Petroleum").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer counter =dataSnapshot.getValue(Integer.class);
                        SubNODP.setText(counter.toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Maths":
                SubjectTitle.setText(str);
                SubjectInformation =FirebaseDatabase.getInstance().getReference("MathsInformation");
                SubjectInformation.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        SubSTLA.setText(dataSnapshot.child("TNOL").getValue(String.class));
                        SubSDOL.setText(dataSnapshot.child("NOLIW").getValue(String.class));

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                mRef.child(user.getUid()).child("Maths").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer counter =dataSnapshot.getValue(Integer.class);
                        SubNODP.setText(counter.toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == SubMark){
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Integer otpread = dataSnapshot.getValue(Integer.class);
                    MarkAttendence(otpread);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void MarkAttendence(Integer otpread) {
        Integer otpwrite = Integer.parseInt(SubOTP.getText().toString().trim());
        Intent intent = getIntent();
        final String str = intent.getStringExtra("Selected Subject");
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        if (otpread == otpwrite){
            Toast.makeText(SubjectActivity.this,"OTP Mached",Toast.LENGTH_LONG).show();
            SubMark.setEnabled(false);
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            final String formattedDate = df.format(c);
            switch (str){
                case "Chemistry":
                    mRef.child(user.getUid()).child("Chemistry").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Integer CCount =dataSnapshot.getValue(Integer.class);
                            mRef.child(user.getUid()).child("Chemistry").setValue(CCount+1);

                            TotalAttendenceCount.child(formattedDate).child("ChemistryTotalCounter").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Integer AttenCount = dataSnapshot.getValue(Integer.class);
                                    TotalAttendenceCount.child(formattedDate).child("ChemistryTotalCounter").setValue(AttenCount+1);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            SubNODP.setText(CCount.toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(SubjectActivity.this,"Attendence Failed",Toast.LENGTH_LONG).show();

                        }
                    });
                    break;
                case "Physics":
                    mRef.child(user.getUid()).child("Physics").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Integer CCount =dataSnapshot.getValue(Integer.class);
                            mRef.child(user.getUid()).child("Physics").setValue(CCount+1);
                            SubNODP.setText(CCount.toString());
                            TotalAttendenceCount.child(formattedDate).child("PhysicsTotalCounter").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Integer AttenCount = dataSnapshot.getValue(Integer.class);
                                    TotalAttendenceCount.child(formattedDate).child("PhysicsTotalCounter").setValue(AttenCount+1);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(SubjectActivity.this,"Attendence Failed",Toast.LENGTH_LONG).show();

                        }
                    });
                    break;
                case "Petroleum":
                    mRef.child(user.getUid()).child("Petroleum").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Integer CCount =dataSnapshot.getValue(Integer.class);
                            mRef.child(user.getUid()).child("Petroleum").setValue(CCount+1);
                            SubNODP.setText(CCount.toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(SubjectActivity.this,"Attendence Failed",Toast.LENGTH_LONG).show();

                        }
                    });
                    TotalAttendenceCount.child(formattedDate).child("PetroleumTotalCounter").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Integer AttenCount = dataSnapshot.getValue(Integer.class);
                            TotalAttendenceCount.child(formattedDate).child("PetroleumTotalCounter").setValue(AttenCount+1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    break;
                case "Maths":
                    mRef.child(user.getUid()).child("Maths").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Integer CCount =dataSnapshot.getValue(Integer.class);
                            mRef.child(user.getUid()).child("Maths").setValue(CCount+1);
                            SubNODP.setText(CCount.toString());
                            TotalAttendenceCount.child(formattedDate).child("MathsTotalCounter").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    Integer AttenCount = dataSnapshot.getValue(Integer.class);
                                    TotalAttendenceCount.child(formattedDate).child("MathsTotalCounter").setValue(AttenCount+1);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(SubjectActivity.this,"Attendence Failed",Toast.LENGTH_LONG).show();

                        }
                    });
                    break;

            }
        }

    }
}
